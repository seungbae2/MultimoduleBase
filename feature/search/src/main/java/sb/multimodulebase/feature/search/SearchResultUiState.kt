package sb.multimodulebase.feature.search

import com.sb.multimodulebase.core.model.Article

interface SearchResultUiState {
    data object Loading : SearchResultUiState

    data object EmptyQuery : SearchResultUiState

    data object LoadFailed : SearchResultUiState

    data class Success(
        val articles: List<Article> = emptyList(),
    ) : SearchResultUiState {
        fun isEmpty(): Boolean = articles.isEmpty()
    }

    /**
     * A state where the search contents are not ready. This happens when the *Fts tables are not
     * populated yet.
     */
    data object SearchNotReady : SearchResultUiState
}