package com.sb.multimodulebase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.sb.multimodulebase.feature.disney.navigation.disneyScreen
import com.sb.multimodulebase.ui.AppState
import sb.multimodulebase.feature.news.navigation.NEWS_ROUTE
import sb.multimodulebase.feature.news.navigation.newsScreen
import sb.multimodulebase.feature.search.navigation.searchScreen

@Composable
fun BaseNavHost(
    appState: AppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = NEWS_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        disneyScreen()
        newsScreen()
        searchScreen(
            onBackClick = navController::popBackStack,
        )
    }
}