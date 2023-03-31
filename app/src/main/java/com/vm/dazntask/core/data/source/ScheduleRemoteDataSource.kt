package com.vm.dazntask.core.data.source

import com.vm.dazntask.core.domain.ScheduleEvent

interface ScheduleRemoteDataSource {

    suspend fun getSchedule(): Array<ScheduleEvent>
}
