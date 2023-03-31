package com.vm.dazntask.core.data.source

import com.vm.dazntask.core.domain.Event
import com.vm.dazntask.core.domain.ScheduleEvent
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.plugins.resources.Resources
import io.ktor.resources.*
import io.ktor.serialization.kotlinx.json.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRemoteDataSource @Inject constructor() :
    EventsRemoteDataSource,
    ScheduleRemoteDataSource {

    private val client = HttpClient(CIO) {
        install(Resources)
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url("https://us-central1-dazn-sandbox.cloudfunctions.net")
        }
    }

    @Resource("/getEvents")
    class Events

    @Resource("/getSchedule")
    class Schedule

    override suspend fun getEvents(): Array<Event> {
        return client.get(Events()).body()
    }

    override suspend fun getSchedule(): Array<ScheduleEvent> {
        return client.get(Schedule()).body()
    }
}
