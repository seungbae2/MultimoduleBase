package com.sb.multimodulebase.core.data.repository

import com.sb.multimodulebase.core.model.Article
import kotlinx.coroutines.flow.Flow

interface SearchNewsRepository {
    suspend fun searchNews(query: String): Flow<List<Article>>
}