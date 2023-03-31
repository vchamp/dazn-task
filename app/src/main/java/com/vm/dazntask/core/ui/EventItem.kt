package com.vm.dazntask.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vm.dazntask.core.ui.model.UiEvent

@Composable
fun EventItem(
    uiEvent: UiEvent,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .height(100.dp)
            .padding(4.dp)
    ) {
        AsyncImage(
            model = uiEvent.imageUrl,
            contentDescription = null,
            Modifier
                .fillMaxHeight()
                .aspectRatio(640 / 480f),
            placeholder = BrushPainter(
                Brush.linearGradient(
                    listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.secondaryContainer
                    )
                )
            )
        )
        Spacer(Modifier.width(8.dp))
        Column(Modifier.fillMaxHeight()) {
            Text(
                uiEvent.title,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                uiEvent.subtitle,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(Modifier.weight(1f))
            Text(
                uiEvent.date,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview
@Composable
fun PreviewEventItem() {
    EventItem(UiEvent("", "Liverpool v Porto", "UEFA Champions League", "Yesterday, 10:30", "", ""))
}
