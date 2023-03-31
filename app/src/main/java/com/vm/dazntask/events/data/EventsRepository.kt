package com.vm.dazntask.events.data

import com.vm.dazntask.core.data.LocalEvent

interface EventsRepository {

    suspend fun getEvents(): Array<LocalEvent>
}