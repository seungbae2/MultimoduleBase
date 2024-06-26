package com.sb.multimodule.core.network.di

import com.sb.multimodule.core.network.DisneyNetworkDataSource
import com.sb.multimodule.core.network.NewsNetworkDataSource
import com.sb.multimodule.core.network.retrofit.DisneyNetwork
import com.sb.multimodule.core.network.retrofit.NewsNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ApiModule {
    @Binds
    fun bindsDisneyNetworkDataSource(impl: DisneyNetwork): DisneyNetworkDataSource

    @Binds
    fun bindsNewNetworkDataSource(impl: NewsNetwork): NewsNetworkDataSource
}