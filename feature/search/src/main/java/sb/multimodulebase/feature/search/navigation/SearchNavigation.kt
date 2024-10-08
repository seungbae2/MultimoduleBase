package sb.multimodulebase.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import sb.multimodulebase.feature.search.SearchRoute

const val SEARCH_ROUTE = "search"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) =
    navigate(SEARCH_ROUTE, navOptions)

fun NavGraphBuilder.searchScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = SEARCH_ROUTE,
    ) {
        SearchRoute(
            onBackClick = onBackClick,
        )
    }
}