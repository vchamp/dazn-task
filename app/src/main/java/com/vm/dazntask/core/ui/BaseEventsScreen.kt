package com.vm.dazntask.core.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.vm.dazntask.core.ui.model.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseEventsScreen(
    isLoading: Boolean,
    events: List<UiEvent>?,
    errorMessageId: Int?,
    onEventClick: (UiEvent) -> Unit = {},
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    LaunchedEffect(errorMessageId) {
        if (errorMessageId != null) {
            snackbarHostState.showSnackbar(
                context.getString(errorMessageId),
                withDismissAction = true,
                duration = SnackbarDuration.Indefinite
            )
        } else {
            snackbarHostState.currentSnackbarData?.dismiss()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { contentPadding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                Column(Modifier.fillMaxSize()) {
                    AnimatedVisibility(events != null, Modifier.fillMaxSize()) {
                        EventsList(events ?: emptyList(), onEventClick)
                    }
                }
                AnimatedVisibility(isLoading, Modifier.align(Alignment.Center)) {
                    CircularProgressIndicator()
                }
            }
        }
    )
}
