package com.sb.multimodulebase.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sb.multimodule.core.common.network.Dispatcher
import com.sb.multimodule.core.common.network.MwDispatchers
import com.sb.multimodule.core.network.NewsNetworkDataSource
import com.sb.multimodulebase.core.model.Article
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    @Dispatcher(MwDispatchers.IO) private val ioDisPatcher: CoroutineDispatcher,
    private val newsNetworkDataSource: NewsNetworkDataSource,
) : NewsRepository {
    override suspend fun getTopHeadlines(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                prefetchDistance = 5,
            ),
            pagingSourceFactory = { NewsPagingSource(newsNetworkDataSource) }
        ).flow
    }
}