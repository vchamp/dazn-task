package com.vm.dazntask.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.vm.dazntask.R
import com.vm.dazntask.app.navigation.DestinationArgs.VIDEO_URL
import com.vm.dazntask.app.navigation.Screens.EVENTS
import com.vm.dazntask.app.navigation.Screens.MAIN
import com.vm.dazntask.app.navigation.Screens.PLAYBACK
import com.vm.dazntask.app.navigation.Screens.SCHEDULE

private object Screens {
    const val MAIN = "main"
    const val EVENTS = "events"
    const val SCHEDULE = "schedule"
    const val PLAYBACK = "playback"
}

object DestinationArgs {
    const val VIDEO_URL = "videoUrl"
}

object Destinations {
    const val MAIN_ROUTE = MAIN
    const val EVENTS_ROUTE = EVENTS
    const val SCHEDULE_ROUTE = SCHEDULE
    const val PLAYBACK_ROUTE = "$PLAYBACK?$VIDEO_URL={$VIDEO_URL}"
}

class NavigationActions(private val navController: NavHostController) {

    fun navigateToPlayback(videoUrl: String, backStackEntry: NavBackStackEntry) {
        if (backStackEntry.lifecycleIsResumed()) {
            navController.navigate("$PLAYBACK?$VIDEO_URL=$videoUrl")
        }
    }
}

enum class TopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val labelTextId: Int
) {
    EVENTS(
        Destinations.EVENTS_ROUTE,
        selectedIcon = Icons.Default.PlayArrow,
        unselectedIcon = Icons.Outlined.PlayArrow,
        labelTextId = R.string.events
    ),
    SCHEDULE(
        Destinations.SCHEDULE_ROUTE,
        selectedIcon = Icons.Default.DateRange,
        unselectedIcon = Icons.Outlined.DateRange,
        labelTextId = R.string.schedule
    )
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.getLifecycle().currentState == Lifecycle.State.RESUMED
