package com.sb.multimodulebase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.sb.multimodulebase.feature.disney.navigation.DISNEY_ROUTE
import com.sb.multimodulebase.feature.disney.navigation.disneyScreen
import com.sb.multimodulebase.ui.AppState

@Composable
fun BaseNavHost(
    appState: AppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = DISNEY_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        disneyScreen()
    }
}