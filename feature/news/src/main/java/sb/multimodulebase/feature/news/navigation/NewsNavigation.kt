package sb.multimodulebase.feature.news.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import sb.multimodulebase.feature.news.NewsRoute

const val NEWS_ROUTE = "news"

fun NavController.navigateToNews(navOptions: NavOptions) = navigate(NEWS_ROUTE, navOptions)

fun NavGraphBuilder.newsScreen() {
    composable(
        route = NEWS_ROUTE,
    ) {
        NewsRoute()
    }
}