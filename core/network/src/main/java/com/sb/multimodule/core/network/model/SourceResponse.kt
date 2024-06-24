package com.sb.multimodule.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceResponse(
    val id: String,
    val name: String
)