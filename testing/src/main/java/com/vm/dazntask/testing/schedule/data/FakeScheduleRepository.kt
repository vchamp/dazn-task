package com.vm.dazntask.testing.schedule.data

import com.vm.dazntask.core.data.LocalEvent
import com.vm.dazntask.schedule.data.ScheduleRepository

class FakeScheduleRepository : ScheduleRepository {

    lateinit var events: Array<LocalEvent>
    var exception: Exception? = null

    override suspend fun getSchedule(): Array<LocalEvent> {
        exception?.let {
            throw it
        }
        return events.copyOf()
    }
}
