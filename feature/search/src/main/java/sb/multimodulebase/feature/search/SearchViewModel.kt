package sb.multimodulebase.feature.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

//    val searchResultUiState: StateFlow<SearchResultUiState>

//    val recentSearchQueriesUiState: StateFlow<RecentSearchQueriesUiState> =
//        recentSearchQueriesUseCase()
//            .map(RecentSearchQueriesUiState::Success)
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5_000),
//                initialValue = RecentSearchQueriesUiState.Loading,
//            )

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    fun onSearchTriggered(query: String) {

    }

    fun clearRecentSearches() {

    }

    fun setNewsResourceBookmarked(newsResourceId: String, isChecked: Boolean) {

    }

    fun followTopic(followedTopicId: String, followed: Boolean) {

    }

    fun setNewsResourceViewed(newsResourceId: String, viewed: Boolean) {

    }
}


private const val SEARCH_QUERY_MIN_LENGTH = 2
private const val SEARCH_MIN_FTS_ENTITY_COUNT = 1
private const val SEARCH_QUERY = "searchQuery"