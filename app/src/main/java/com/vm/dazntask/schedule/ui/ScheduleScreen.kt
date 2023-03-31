package com.vm.dazntask.schedule.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vm.dazntask.core.ui.BaseEventsScreen

@Composable
fun ScheduleScreen(viewModel: ScheduleViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    BaseEventsScreen(
        state.isLoading,
        state.events,
        state.errorMessageId
    )
}
