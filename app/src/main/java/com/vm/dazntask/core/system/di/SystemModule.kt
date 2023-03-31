package com.vm.dazntask.core.system.di

import com.vm.dazntask.core.system.connectivity.ConnectivityStatusProvider
import com.vm.dazntask.core.system.connectivity.NetworkConnectivityStatusProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SystemModule {

    @Binds
    fun bindConnectivityStatusProvider(
        provider: NetworkConnectivityStatusProvider
    ): ConnectivityStatusProvider
}
