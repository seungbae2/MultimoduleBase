package com.sb.multimodule.core.network.di

import com.sb.multimodule.core.network.DisneyNetworkDataSource
import com.sb.multimodule.core.network.retrofit.DisneyNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface FlavoredNetworkModule {
    @Binds
    fun bindsDisneyNetworkDataSource(impl: DisneyNetwork): DisneyNetworkDataSource
}