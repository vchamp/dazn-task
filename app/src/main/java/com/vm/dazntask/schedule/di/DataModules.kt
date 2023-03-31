package com.vm.dazntask.schedule.di

import com.vm.dazntask.core.data.source.NetworkRemoteDataSource
import com.vm.dazntask.core.data.source.ScheduleRemoteDataSource
import com.vm.dazntask.schedule.data.DefaultScheduleRepository
import com.vm.dazntask.schedule.data.ScheduleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindScheduleRepository(
        repository: DefaultScheduleRepository
    ): ScheduleRepository
}

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindScheduleRemoteDataSource(
        dataSource: NetworkRemoteDataSource
    ): ScheduleRemoteDataSource
}
