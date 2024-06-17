package com.sb.multimodulebase.feature.disney.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sb.multimodulebase.feature.disney.DisneyRoute

const val DISNEY_ROUTE = "disney"

fun NavController.navigateToDisney(navOptions: NavOptions) = navigate(DISNEY_ROUTE, navOptions)

fun NavGraphBuilder.disneyScreen() {
    composable(
        route = DISNEY_ROUTE,
    ) {
        DisneyRoute()
    }
}