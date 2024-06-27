package com.sb.multimodulebase.core.data.repository

import com.sb.multimodulebase.core.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getTopHeadlines(country: String): Flow<List<Article>>
}