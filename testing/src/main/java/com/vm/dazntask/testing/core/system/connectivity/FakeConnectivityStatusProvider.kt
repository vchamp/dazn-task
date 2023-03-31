package com.vm.dazntask.testing.core.system.connectivity

import com.vm.dazntask.core.system.connectivity.ConnectivityStatus
import com.vm.dazntask.core.system.connectivity.ConnectivityStatusProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeConnectivityStatusProvider : ConnectivityStatusProvider {

    val status = MutableStateFlow(ConnectivityStatus.AVAILABLE)

    override fun observe(): Flow<ConnectivityStatus> =
        status
}
