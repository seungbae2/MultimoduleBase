package com.sb.multimodulebase.core.domain

import com.sb.multimodulebase.core.data.repository.NewsRepository
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
){
    suspend operator fun invoke(country: String) = newsRepository.getTopHeadlines(country = country)
}