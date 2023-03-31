package com.vm.dazntask.events.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vm.dazntask.R
import com.vm.dazntask.core.system.connectivity.ConnectivityStatus
import com.vm.dazntask.core.system.connectivity.ConnectivityStatusProvider
import com.vm.dazntask.events.data.EventsRepository
import com.vm.dazntask.core.ui.model.UiEvent
import com.vm.dazntask.core.ui.usecase.FormatEventTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventsRepository: EventsRepository,
    private val connectivityStatusProvider: ConnectivityStatusProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(EventsUiState())
    val uiState = _uiState.asStateFlow()

    private val convertTimeToString = FormatEventTimeUseCase()

    init {
        viewModelScope.launch {
            loadEvents()
        }
    }

    private suspend fun loadEvents() {
        try {
            _uiState.update {
                it.copy(isLoading = true)
            }
            val events = eventsRepository.getEvents().asSequence()
                .sortedBy { it.localTime }
                .map {
                    UiEvent(
                        id = it.id,
                        title = it.title,
                        subtitle = it.subtitle,
                        date = convertTimeToString(it.localTime),
                        imageUrl = it.imageUrl,
                        videoUrl = it.videoUrl
                    )
                }
                .toList()
            _uiState.value = EventsUiState(
                isLoading = false,
                events = events,
                errorMessageId = null
            )
        } catch (e: Exception) {
            if (e is CancellationException) {
                throw e
            }
            Log.e("Events", "Error getting events: $e")
            _uiState.update {
                it.copy(
                    isLoading = false,
                    errorMessageId = R.string.error_no_connection
                )
            }
            observeConnectivity()
        }
    }

    private fun observeConnectivity() {
        viewModelScope.launch {
            connectivityStatusProvider.observe()
                .collectLatest { status ->
                    Log.i("Events", "Network status: $status")
                    if (status == ConnectivityStatus.AVAILABLE) {
                        loadEvents()
                    }
                }
        }
    }

    data class EventsUiState(
        val isLoading: Boolean = false,
        val events: List<UiEvent>? = null,
        val errorMessageId: Int? = null
    )
}
