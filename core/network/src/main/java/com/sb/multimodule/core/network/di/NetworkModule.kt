package com.sb.multimodule.core.network.di

import androidx.tracing.trace
import com.sb.multimodule.core.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {
    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory = trace("DisneyOkHttpClient") {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    },
            )
            .build()
    }

    @Named("news")
    @Provides
    @Singleton
    fun okHttpCallFactoryNews(): Call.Factory = trace("NewsOkHttpClient") {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    },
            )
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("X-Api-Key", BuildConfig.NEWS_API_KEY) // Add the API key to the request header
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }
}