package com.vm.dazntask.core.system.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityStatusProvider {

    fun observe(): Flow<ConnectivityStatus>
}
