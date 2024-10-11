package com.sb.multimodulebase.core.data.di

import com.sb.multimodulebase.core.data.repository.DisneyRepository
import com.sb.multimodulebase.core.data.repository.DisneyRepositoryImpl
import com.sb.multimodulebase.core.data.repository.NewsRepository
import com.sb.multimodulebase.core.data.repository.NewsRepositoryImpl
import com.sb.multimodulebase.core.data.repository.SearchNewsRepository
import com.sb.multimodulebase.core.data.repository.SearchNewsRepositoryImpl
import com.sb.multimodulebase.core.data.util.LocationBroadcastMonitor
import com.sb.multimodulebase.core.data.util.LocationMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindsDisneyRepository(
        disneyRepositoryImpl: DisneyRepositoryImpl
    ): DisneyRepository

    @Binds
    abstract fun bindsNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository

    @Binds
    abstract fun bindsSearchNewsRepository(
        searchNewsRepositoryImpl: SearchNewsRepositoryImpl
    ): SearchNewsRepository

    @Binds
    internal abstract fun binds(impl: LocationBroadcastMonitor): LocationMonitor
}