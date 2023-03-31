package com.vm.dazntask.core.data.source

import com.vm.dazntask.core.domain.Event

interface EventsRemoteDataSource {

    suspend fun getEvents(): Array<Event>
}
