package com.vm.dazntask.events.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vm.dazntask.core.ui.BaseEventsScreen

@Composable
fun EventsScreen(
    onNavigateToPlayback: (videoUrl: String) -> Unit,
    viewModel: EventsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    BaseEventsScreen(
        state.isLoading,
        state.events,
        state.errorMessageId
    ) {
        it.videoUrl?.let { url ->
            onNavigateToPlayback(url)
        }
    }
}
