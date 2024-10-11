package com.sb.multimodulebase.core.domain

import com.sb.multimodulebase.core.data.repository.SearchNewsRepository
import javax.inject.Inject

class GetSearchNewsUseCase @Inject constructor(
    private val searchNewsRepository: SearchNewsRepository,
) {
    suspend operator fun invoke(searchQuery: String) = searchNewsRepository.searchNews(searchQuery)
}