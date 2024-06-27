package sb.multimodulebase.feature.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sb.multimodule.core.common.result.Result
import com.sb.multimodule.core.common.result.asResult
import com.sb.multimodulebase.core.domain.GetTopHeadlinesUseCase
import com.sb.multimodulebase.core.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {

    private val _newsUiState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    val newsUiState: StateFlow<NewsUiState> = _newsUiState

    init {
        viewModelScope.launch {
            getTopHeadlinesUseCase.invoke(country = "us")
                .asResult()
                .map { topHeadlinesResult ->
                    when (topHeadlinesResult) {
                        is Result.Error -> NewsUiState.Error
                        is Result.Loading -> NewsUiState.Loading
                        is Result.Success -> NewsUiState.Success(topHeadlinesResult.data)
                    }
                }
                .collect { state ->
                    _newsUiState.value = state
                }
        }
    }
}

sealed interface NewsUiState {
    data class Success(val news: List<Article>) : NewsUiState
    data object Error : NewsUiState
    data object Loading : NewsUiState
}