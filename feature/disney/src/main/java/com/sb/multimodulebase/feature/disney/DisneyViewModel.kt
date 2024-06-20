package com.sb.multimodulebase.feature.disney

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sb.multimodulebase.core.domain.GetAllDisneyCharacterUseCase
import com.sb.multimodulebase.core.model.DisneyCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisneyViewModel @Inject constructor(
    private val getAllDisneyCharacterUseCase: GetAllDisneyCharacterUseCase
) : ViewModel() {

    private val _disneyCharactersFlow = MutableStateFlow<PagingData<DisneyCharacter>>(PagingData.empty())
    val disneyCharactersFlow: StateFlow<PagingData<DisneyCharacter>> = _disneyCharactersFlow

    init {
        viewModelScope.launch {
            getAllDisneyCharacterUseCase.invoke()
                .cachedIn(viewModelScope)
                .collect {
                    _disneyCharactersFlow.value = it
                }
        }
    }
}