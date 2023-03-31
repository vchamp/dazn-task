package com.vm.dazntask.schedule.data

import com.vm.dazntask.core.data.LocalEvent

interface ScheduleRepository {

    suspend fun getSchedule(): Array<LocalEvent>
}
