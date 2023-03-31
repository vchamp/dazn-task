package com.vm.dazntask.events.ui

import TEST_EVENTS
import com.vm.dazntask.R
import com.vm.dazntask.core.system.connectivity.ConnectivityStatus
import com.vm.dazntask.testing.MainDispatcherRule
import com.vm.dazntask.testing.core.system.connectivity.FakeConnectivityStatusProvider
import com.vm.dazntask.testing.events.data.FakeEventsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EventsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val eventsRepository = FakeEventsRepository()
    private val connectivityStatusProvider = FakeConnectivityStatusProvider()

    private lateinit var viewModel: EventsViewModel

    @Before
    fun setUp() {
        connectivityStatusProvider.status.value = ConnectivityStatus.AVAILABLE
    }

    @Test
    fun `When created, loads events`() = runTest {
        eventsRepository.events = TEST_EVENTS
        viewModel = EventsViewModel(eventsRepository, connectivityStatusProvider)
        assertEquals(TEST_EVENTS.size, viewModel.uiState.value.events?.size)
    }

    @Test
    fun `When loads events, must be sorted`() = runTest {
        eventsRepository.events = TEST_EVENTS
        viewModel = EventsViewModel(eventsRepository, connectivityStatusProvider)
        val events = viewModel.uiState.value.events ?: error("Events must be not null")
        assertTrue(events[0].id == "2")
    }

    @Test
    fun `When connectivity is restored, loads events`() = runTest {
        connectivityStatusProvider.status.value = ConnectivityStatus.LOST
        eventsRepository.exception = Exception("No connection")
        viewModel = EventsViewModel(eventsRepository, connectivityStatusProvider)
        assertNull(viewModel.uiState.value.events)
        assertEquals(R.string.error_no_connection, viewModel.uiState.value.errorMessageId)
        eventsRepository.apply {
            events = TEST_EVENTS
            exception = null
        }
        connectivityStatusProvider.status.value = ConnectivityStatus.AVAILABLE
        assertEquals(TEST_EVENTS.size, viewModel.uiState.value.events?.size)
        assertNull(viewModel.uiState.value.errorMessageId)
    }
}
