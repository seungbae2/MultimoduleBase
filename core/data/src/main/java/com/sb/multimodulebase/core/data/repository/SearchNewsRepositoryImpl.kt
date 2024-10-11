package com.sb.multimodulebase.core.data.repository

import com.sb.multimodule.core.network.NewsNetworkDataSource
import com.sb.multimodulebase.core.data.mapper.toExternalData
import com.sb.multimodulebase.core.model.Article
import com.skydoves.sandwich.toFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsRepositoryImpl @Inject constructor(
    private val newsNetworkDataSource: NewsNetworkDataSource,
) : SearchNewsRepository {
    override suspend fun searchNews(query: String): Flow<List<Article>> {
        val response = newsNetworkDataSource.getEverything(query)
        return response.toFlow {
            articles.map { article ->
                article.toExternalData()
            }
        }
    }
}