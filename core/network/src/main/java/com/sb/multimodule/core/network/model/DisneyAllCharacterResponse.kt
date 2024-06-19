package com.sb.multimodule.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class DisneyAllCharacterResponse(
    val info: InfoResponse,
    val data: List<DisneyCharacterResponse>
)

