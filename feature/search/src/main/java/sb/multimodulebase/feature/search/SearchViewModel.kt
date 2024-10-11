package sb.multimodulebase.feature.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sb.multimodulebase.core.domain.GetSearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    getSearchNewsUseCase: GetSearchNewsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    val searchResultUiState: StateFlow<SearchResultUiState> =
        searchQuery.flatMapLatest { query ->
            if (query.length < SEARCH_QUERY_MIN_LENGTH) {
                flowOf(SearchResultUiState.EmptyQuery)
            } else {
                getSearchNewsUseCase(query)
                    .map { (SearchResultUiState.Success(it)) }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SearchResultUiState.Loading,
        )

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }
}


private const val SEARCH_QUERY_MIN_LENGTH = 2
private const val SEARCH_MIN_FTS_ENTITY_COUNT = 1
private const val SEARCH_QUERY = "searchQuery"