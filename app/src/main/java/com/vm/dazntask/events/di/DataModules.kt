package com.vm.dazntask.events.di

import com.vm.dazntask.core.data.source.EventsRemoteDataSource
import com.vm.dazntask.core.data.source.NetworkRemoteDataSource
import com.vm.dazntask.events.data.DefaultEventsRepository
import com.vm.dazntask.events.data.EventsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindEventsRepository(
        repository: DefaultEventsRepository
    ): EventsRepository
}

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindEventsRemoteDataSource(
        dataSource: NetworkRemoteDataSource
    ): EventsRemoteDataSource
}
