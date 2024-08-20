package sb.multimodulebase.feature.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sb.multimodulebase.core.domain.GetTopHeadlinesUseCase
import com.sb.multimodulebase.core.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {

    private val _newsArticleFlow = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val newsArticleFlow: StateFlow<PagingData<Article>> = _newsArticleFlow

    init {
        viewModelScope.launch {
            getTopHeadlinesUseCase.invoke()
                .cachedIn(viewModelScope)
                .collect { state ->
                    _newsArticleFlow.value = state
                }
        }
    }
}