package com.sb.multimodulebase.core.domain

import com.sb.multimodulebase.core.data.repository.DisneyRepository
import javax.inject.Inject

class GetAllDisneyCharacterUseCase @Inject constructor(
    private val disneyRepository: DisneyRepository
) {
    suspend operator fun invoke() = disneyRepository.getAllCharacters()
}