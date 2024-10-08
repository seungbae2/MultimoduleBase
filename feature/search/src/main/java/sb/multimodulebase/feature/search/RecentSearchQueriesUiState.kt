package sb.multimodulebase.feature.search

import com.sb.multimodulebase.core.model.RecentSearchQuery

interface RecentSearchQueriesUiState {
    data object Loading : RecentSearchQueriesUiState

    data class Success(
        val recentQueries: List<RecentSearchQuery> = emptyList(),
    ) : RecentSearchQueriesUiState
}