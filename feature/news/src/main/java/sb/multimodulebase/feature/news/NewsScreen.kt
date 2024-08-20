package sb.multimodulebase.feature.news

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sb.multimodulebase.core.model.Article

@Composable
internal fun NewsRoute(
    viewModel: NewsViewModel = hiltViewModel(),
) {
    val newsArticlePagingItems: LazyPagingItems<Article> =
        viewModel.newsArticleFlow.collectAsLazyPagingItems()
    NewsScreen(newsArticlePagingItems)
}

@Composable
internal fun NewsScreen(
    newsArticlePagingItems: LazyPagingItems<Article>
) {
    LazyColumn {
        items(newsArticlePagingItems.itemCount) { index ->
            newsArticlePagingItems[index]?.let { article ->
                ArticleCard(article = article)
            }
        }
    }
}