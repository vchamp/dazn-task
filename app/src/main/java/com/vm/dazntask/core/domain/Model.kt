package com.vm.dazntask.core.domain

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageUrl: String,
    val videoUrl: String
)

@Serializable
data class ScheduleEvent(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageUrl: String
)
