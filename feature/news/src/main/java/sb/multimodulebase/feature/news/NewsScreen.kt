package sb.multimodulebase.feature.news

import android.content.Context
import android.net.Uri
import androidx.annotation.ColorInt
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sb.multimodulebase.core.model.Article
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun NewsRoute(
    viewModel: NewsViewModel = hiltViewModel(),
) {
    val newsArticlePagingItems: LazyPagingItems<Article> =
        viewModel.newsArticleFlow.collectAsLazyPagingItems()
    NewsScreen(
        newsArticlePagingItems = newsArticlePagingItems,
        getTopHeadlines = viewModel::getTopHeadlines
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NewsScreen(
    newsArticlePagingItems: LazyPagingItems<Article>,
    getTopHeadlines: () -> Unit,
) {
    val context = LocalContext.current
    val backgroundColor = MaterialTheme.colorScheme.background.toArgb()
    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()
    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            getTopHeadlines()
            // delay 없으면 indicator 사라지지 않음 1.4.0-alpha03에서 이슈 해결됨
            delay(1000L)
            isRefreshing = false
        }
    }

    PullToRefreshBox(
        state = pullRefreshState,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(newsArticlePagingItems.itemCount) { index ->
                newsArticlePagingItems[index]?.let { article ->
                    ArticleCard(
                        article = article,
                        onClickArticle = {
                            launchCustomChromeTab(context, Uri.parse(article.url), backgroundColor)
                        }
                    )
                }
            }
        }
    }
}

fun launchCustomChromeTab(context: Context, uri: Uri, @ColorInt toolbarColor: Int) {
    val customTabBarColor = CustomTabColorSchemeParams.Builder()
        .setToolbarColor(toolbarColor).build()
    val customTabsIntent = CustomTabsIntent.Builder()
        .setDefaultColorSchemeParams(customTabBarColor)
        .build()

    customTabsIntent.launchUrl(context, uri)
}