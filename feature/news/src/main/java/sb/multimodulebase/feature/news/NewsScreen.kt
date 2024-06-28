package sb.multimodulebase.feature.news

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun NewsRoute(
    viewModel: NewsViewModel = hiltViewModel(),
) {
    val newsUiState: NewsUiState by viewModel.newsUiState.collectAsStateWithLifecycle()
    NewsScreen(newsUiState)
}

@Composable
internal fun NewsScreen(
    newsUiState: NewsUiState
) {
    LazyColumn {
        when (newsUiState) {
            NewsUiState.Error -> item { Text(text = "Error") }
            NewsUiState.Loading -> item { Text(text = "Loading") }
            is NewsUiState.Success -> {
                items(newsUiState.news.size) { index ->
                    ArticleCard(newsUiState.news[index])
                }
            }
        }
    }
}