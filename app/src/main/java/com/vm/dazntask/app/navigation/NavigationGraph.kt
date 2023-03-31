package com.vm.dazntask.app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.vm.dazntask.events.ui.EventsScreen
import com.vm.dazntask.playback.ui.PlaybackScreen
import com.vm.dazntask.schedule.ui.ScheduleScreen

@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    startDestination: String,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {
    NavHost(
        navController,
        startDestination = startDestination,
        Modifier.padding(innerPadding)
    ) {
        navigation(
            route = Destinations.MAIN_ROUTE,
            startDestination = Destinations.EVENTS_ROUTE
        ) {
            composable(Destinations.EVENTS_ROUTE) { backStackEntry ->
                EventsScreen(
                    onNavigateToPlayback = { navActions.navigateToPlayback(it, backStackEntry) }
                )
            }
            composable(Destinations.SCHEDULE_ROUTE) {
                ScheduleScreen()
            }
        }
        composable(Destinations.PLAYBACK_ROUTE) {
            PlaybackScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
