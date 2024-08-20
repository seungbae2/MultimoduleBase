package com.sb.multimodulebase.core.data.repository

import androidx.paging.PagingData
import com.sb.multimodulebase.core.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getTopHeadlines(): Flow<PagingData<Article>>
}