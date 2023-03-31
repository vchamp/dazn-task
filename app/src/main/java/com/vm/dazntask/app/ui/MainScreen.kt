package com.vm.dazntask.app.ui

import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vm.dazntask.app.navigation.Destinations
import com.vm.dazntask.app.navigation.MainNavigationGraph
import com.vm.dazntask.app.navigation.TopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val isTopLevelDestination =
                TopLevelDestination.values().any { it.route == currentDestination?.route }
            if (isTopLevelDestination) {
                AppBottomBar(
                    TopLevelDestination.values().asList(),
                    { item ->
                        navController.navigate(item.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    },
                    currentDestination,
                    Modifier.height(80.dp)
                )
            }
        }
    ) { innerPadding ->
        MainNavigationGraph(navController, innerPadding, Destinations.MAIN_ROUTE)
    }
}

@Composable
private fun AppBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier
    ) {
        destinations.forEach { item ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == item.route } == true
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = stringResource(item.labelTextId)
                    )
                },
                label = { Text(stringResource(item.labelTextId)) },
                selected = selected,
                onClick = { onNavigateToDestination(item) }
            )
        }
    }
}
