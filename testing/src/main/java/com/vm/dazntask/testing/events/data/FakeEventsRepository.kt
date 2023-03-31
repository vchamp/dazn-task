package com.vm.dazntask.testing.events.data

import com.vm.dazntask.core.data.LocalEvent
import com.vm.dazntask.events.data.EventsRepository

class FakeEventsRepository : EventsRepository {

    lateinit var events: Array<LocalEvent>
    var exception: Exception? = null

    override suspend fun getEvents(): Array<LocalEvent> {
        exception?.let {
            throw it
        }
        return events
    }
}
