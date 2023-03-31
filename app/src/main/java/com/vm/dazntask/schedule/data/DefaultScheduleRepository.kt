package com.vm.dazntask.schedule.data

import com.vm.dazntask.core.data.LocalEvent
import com.vm.dazntask.core.data.source.ScheduleRemoteDataSource
import com.vm.dazntask.core.domain.ConvertStringToLocalTimeUseCase
import javax.inject.Inject

class DefaultScheduleRepository @Inject constructor(
    private val dataSource: ScheduleRemoteDataSource
) : ScheduleRepository {

    private val convertStringToTime = ConvertStringToLocalTimeUseCase()

    override suspend fun getSchedule(): Array<LocalEvent> {
        return dataSource.getSchedule().map {
            LocalEvent(
                id = it.id,
                title = it.title,
                subtitle = it.subtitle,
                localTime = convertStringToTime(it.date),
                imageUrl = it.imageUrl,
                videoUrl = null
            )
        }.toTypedArray()
    }
}
