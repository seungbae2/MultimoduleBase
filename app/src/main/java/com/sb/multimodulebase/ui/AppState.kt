package com.sb.multimodulebase.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sb.multimodulebase.feature.disney.navigation.DISNEY_ROUTE
import com.sb.multimodulebase.feature.disney.navigation.navigateToDisney
import com.sb.multimodulebase.navigation.TopLevelDestination
import com.sb.multimodulebase.navigation.TopLevelDestination.DISNEY
import com.sb.multimodulebase.navigation.TopLevelDestination.NEWS
import kotlinx.coroutines.CoroutineScope
import sb.multimodulebase.feature.news.navigation.NEWS_ROUTE
import sb.multimodulebase.feature.news.navigation.navigateToNews
import sb.multimodulebase.feature.search.navigation.navigateToSearch

@Composable
fun rememberAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): AppState {
    return remember(
        navController,
        coroutineScope,
        windowSizeClass,
    ) {
        AppState(
            navController,
            coroutineScope,
            windowSizeClass,
        )
    }
}

@Stable
class AppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            DISNEY_ROUTE -> DISNEY
            NEWS_ROUTE -> NEWS
            else -> null
        }


    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (topLevelDestination) {
            DISNEY -> navController.navigateToDisney(topLevelNavOptions)
            NEWS -> navController.navigateToNews(topLevelNavOptions)
        }
    }

    fun navigateToSearch() = navController.navigateToSearch()
}