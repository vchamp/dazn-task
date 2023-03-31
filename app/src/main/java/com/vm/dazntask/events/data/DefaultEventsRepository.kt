package com.vm.dazntask.events.data

import com.vm.dazntask.core.data.LocalEvent
import com.vm.dazntask.core.data.source.EventsRemoteDataSource
import com.vm.dazntask.core.domain.ConvertStringToLocalTimeUseCase
import javax.inject.Inject

class DefaultEventsRepository @Inject constructor(
    private val dataSource: EventsRemoteDataSource
) : EventsRepository {

    private val convertStringToTime = ConvertStringToLocalTimeUseCase()

    override suspend fun getEvents(): Array<LocalEvent> {
        return dataSource.getEvents().map {
            LocalEvent(
                id = it.id,
                title = it.title,
                subtitle = it.subtitle,
                localTime = convertStringToTime(it.date),
                imageUrl = it.imageUrl,
                videoUrl = it.videoUrl
            )
        }.toTypedArray()
    }
}
