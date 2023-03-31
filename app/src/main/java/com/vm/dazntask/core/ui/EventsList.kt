package com.vm.dazntask.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vm.dazntask.core.ui.model.UiEvent

@Composable
fun EventsList(
    events: List<UiEvent>,
    onEventClick: (UiEvent) -> Unit = {}
) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
    ) {
        itemsIndexed(events) { index, item ->
            EventItem(item,
                Modifier
                    .fillMaxWidth()
                    .clickable { onEventClick(item) }
            )
            if (index < events.lastIndex) {
                Divider()
            }
        }
    }
}
