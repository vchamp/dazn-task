package com.vm.dazntask.schedule.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vm.dazntask.R
import com.vm.dazntask.core.system.connectivity.ConnectivityStatus
import com.vm.dazntask.core.system.connectivity.ConnectivityStatusProvider
import com.vm.dazntask.schedule.data.ScheduleRepository
import com.vm.dazntask.core.ui.model.UiEvent
import com.vm.dazntask.core.ui.usecase.FormatFutureTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
    private val connectivityStatusProvider: ConnectivityStatusProvider
) : ViewModel() {

    companion object {

        val reloadDelay = 30.seconds
    }

    private val _uiState = MutableStateFlow(ScheduleUiState())
    val uiState = _uiState.asStateFlow()

    private val active = _uiState.subscriptionCount
        .map { count -> count > 0 }
        .distinctUntilChanged()
        .onEach { isActive ->
            if (isActive) onActive() else onInactive()
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            false
        )

    private val convertTimeToString = FormatFutureTimeUseCase()

    private var reloadingJob: Job? = null

    private fun onActive() {
        Log.d("Schedule", "Reload active")
        startReloadingJob()
    }

    private fun onInactive() {
        Log.d("Schedule", "Reload inactive")
        reloadingJob?.cancel()
    }

    private fun startReloadingJob() {
        reloadingJob = viewModelScope.launch {
            try {
                while (true) {
                    reloadSchedule()
                    delay(reloadDelay)
                }
            } catch (e: Exception) {
                if (e is CancellationException) {
                    throw e
                }
                Log.e("Schedule", "Error getting schedule: $e")
                _uiState.update {
                    it.copy(errorMessageId = R.string.error_no_connection)
                }
                observeConnectivity()
            }
        }.apply {
            invokeOnCompletion { cause ->
                Log.i("Schedule", "Stopped continuous reload, cause: $cause")
            }
        }
    }

    private suspend fun reloadSchedule() {
        try {
            _uiState.update {
                it.copy(isLoading = true)
            }
            val events = scheduleRepository.getSchedule()
                .asSequence()
                .sortedBy { it.localTime }
                .map {
                    UiEvent(
                        id = it.id,
                        title = it.title,
                        subtitle = it.subtitle,
                        date = convertTimeToString(it.localTime),
                        imageUrl = it.imageUrl,
                        videoUrl = null
                    )
                }.toList()
            Log.i("Schedule", "Loaded events: ${events.size}")
            _uiState.value = ScheduleUiState(
                events = events,
                errorMessageId = null
            )
        } finally {
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    private fun observeConnectivity() {
        viewModelScope.launch {
            connectivityStatusProvider.observe()
                .collectLatest { status ->
                    Log.i("Schedule", "Network status: $status")
                    if (status == ConnectivityStatus.AVAILABLE) {
                        if (active.value) {
                            startReloadingJob()
                        }
                    }
                }
        }
    }

    data class ScheduleUiState(
        val isLoading: Boolean = false,
        val events: List<UiEvent>? = null,
        val errorMessageId: Int? = null
    )
}
