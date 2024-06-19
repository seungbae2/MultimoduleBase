package com.sb.multimodulebase.core.data.repository

import androidx.paging.PagingData
import com.sb.multimodulebase.core.model.DisneyCharacter
import kotlinx.coroutines.flow.Flow

interface DisneyRepository {
    suspend fun getAllCharacters(): Flow<PagingData<DisneyCharacter>>
}