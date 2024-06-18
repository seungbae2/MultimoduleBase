package com.sb.multimodule.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sb.multimodule.core.network.DisneyNetworkDataSource
import com.sb.multimodule.core.network.model.DisneyAllCharacterResponse
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton


private interface DisneyNetworkApi {
    @GET("character")
    suspend fun getAllCharacters() : ApiResponse<DisneyAllCharacterResponse>
}

@Singleton
internal class DisneyNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : DisneyNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl("https://api.disneyapi.dev/")
        .callFactory { okhttpCallFactory.get().newCall(it) }
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        .build()
        .create(DisneyNetworkApi::class.java)

    override suspend fun getAllCharacters(): ApiResponse<DisneyAllCharacterResponse> =
        networkApi.getAllCharacters()
}