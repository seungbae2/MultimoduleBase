package com.sb.multimodulebase.core.data.di

import com.sb.multimodulebase.core.data.repository.DisneyRepository
import com.sb.multimodulebase.core.data.repository.DisneyRepositoryImpl
import com.sb.multimodulebase.core.data.repository.NewsRepository
import com.sb.multimodulebase.core.data.repository.NewsRepositoryImpl
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
    ) : DisneyRepository

    @Binds
    abstract fun bindsNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ) : NewsRepository
}