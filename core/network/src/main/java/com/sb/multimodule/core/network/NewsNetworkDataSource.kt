package com.sb.multimodule.core.network

import com.sb.multimodule.core.network.model.TopHeadlinesResponse
import com.skydoves.sandwich.ApiResponse

interface NewsNetworkDataSource {
    suspend fun getTopHeadlines(country: String) : ApiResponse<TopHeadlinesResponse>
}