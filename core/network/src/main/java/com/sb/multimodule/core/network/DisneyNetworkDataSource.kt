package com.sb.multimodule.core.network

import com.sb.multimodule.core.network.model.DisneyAllCharacterResponse
import com.skydoves.sandwich.ApiResponse

interface DisneyNetworkDataSource {
    suspend fun getAllCharacters(): ApiResponse<DisneyAllCharacterResponse>
}