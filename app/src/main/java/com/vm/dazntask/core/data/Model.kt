package com.vm.dazntask.core.data

import java.time.LocalDateTime

data class LocalEvent(
    val id: String,
    val title: String,
    val subtitle: String,
    val localTime: LocalDateTime,
    val imageUrl: String,
    val videoUrl: String?
)
