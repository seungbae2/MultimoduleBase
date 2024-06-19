package com.sb.multimodulebase.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sb.multimodule.core.common.network.Dispatcher
import com.sb.multimodule.core.common.network.MwDispatchers
import com.sb.multimodule.core.network.DisneyNetworkDataSource
import com.sb.multimodulebase.core.model.DisneyCharacter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DisneyRepositoryImpl @Inject constructor(
    @Dispatcher(MwDispatchers.IO) private val ioDisPatcher: CoroutineDispatcher,
    private val disneyNetworkDataSource: DisneyNetworkDataSource
): DisneyRepository {
    override suspend fun getAllCharacters(): Flow<PagingData<DisneyCharacter>> {
        return Pager(
            config = PagingConfig(
                pageSize = 50,
                prefetchDistance = 5,
            ),
            pagingSourceFactory = { DisneyPagingSource(disneyNetworkDataSource) }
        ).flow
    }
}