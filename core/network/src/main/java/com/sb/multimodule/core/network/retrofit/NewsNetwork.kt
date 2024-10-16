package com.sb.multimodule.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sb.multimodule.core.network.NewsNetworkDataSource
import com.sb.multimodule.core.network.model.EverythingResponse
import com.sb.multimodule.core.network.model.TopHeadlinesResponse
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

private interface NewsNetworkApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
    ): ApiResponse<TopHeadlinesResponse>

    @GET("everything")
    suspend fun getEverything(
        @Query("q") query: String,
    ): ApiResponse<EverythingResponse>
}

@Singleton
internal class NewsNetwork @Inject constructor(
    networkJson: Json,
    @Named("news") okhttpCallFactory: dagger.Lazy<Call.Factory>
) : NewsNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .callFactory { okhttpCallFactory.get().newCall(it) }
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType())
        )
        .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        .build()
        .create(NewsNetworkApi::class.java)

    override suspend fun getTopHeadlines(
        country: String,
        pageSize: Int,
        page: Int
    ): ApiResponse<TopHeadlinesResponse> =
        networkApi.getTopHeadlines(
            country = country,
            pageSize = pageSize,
            page = page
        )

    override suspend fun getEverything(query: String): ApiResponse<EverythingResponse> =
        networkApi.getEverything(query = query)
}