package com.sb.multimodulebase.core.data.repository

import com.sb.multimodule.core.common.network.Dispatcher
import com.sb.multimodule.core.common.network.MwDispatchers
import com.sb.multimodule.core.network.NewsNetworkDataSource
import com.sb.multimodulebase.core.data.mapper.toExternalData
import com.sb.multimodulebase.core.model.Article
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    @Dispatcher(MwDispatchers.IO) private val ioDisPatcher: CoroutineDispatcher,
    private val newsNetworkDataSource: NewsNetworkDataSource,
) : NewsRepository {
    override suspend fun getTopHeadlines(country: String): Flow<List<Article>> = flow {
        val response = newsNetworkDataSource.getTopHeadlines(country)
        response.suspendOnSuccess {
            emit(data.articles.map { it.toExternalData() })
        }.onFailure {
            Timber.e(message())
        }
    }
}