package com.sb.multimodulebase.core.data.repository

import com.sb.multimodule.core.common.network.Dispatcher
import com.sb.multimodule.core.common.network.MwDispatchers
import com.sb.multimodule.core.network.DisneyNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DisneyRepositoryImpl @Inject constructor(
    @Dispatcher(MwDispatchers.IO) private val ioDisPatcher: CoroutineDispatcher,
    private val disneyNetworkDataSource: DisneyNetworkDataSource
): DisneyRepository {
}