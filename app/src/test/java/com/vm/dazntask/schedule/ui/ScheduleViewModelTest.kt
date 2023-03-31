package com.vm.dazntask.schedule.ui

import TEST_EVENTS
import com.vm.dazntask.testing.MainDispatcherRule
import com.vm.dazntask.testing.core.system.connectivity.FakeConnectivityStatusProvider
import com.vm.dazntask.testing.schedule.data.FakeScheduleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ScheduleViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(StandardTestDispatcher())

    private val scheduleRepository = FakeScheduleRepository()
    private val connectivityStatusProvider = FakeConnectivityStatusProvider()

    private lateinit var viewModel: ScheduleViewModel

    @Test
    fun `When active, continuously reloads schedule`() = runTest {
        scheduleRepository.events = TEST_EVENTS
        viewModel = ScheduleViewModel(scheduleRepository, connectivityStatusProvider)
        val takes = 10
        var collected = 0
        viewModel.uiState
            .filter { !it.isLoading && it.events != null }
            .take(takes)
            .onEach {
                collected++
            }.launchIn(CoroutineScope(UnconfinedTestDispatcher(testScheduler)))
        advanceTimeBy((ScheduleViewModel.reloadDelay * takes).inWholeMilliseconds)
        assertEquals(takes, collected)
    }
}
