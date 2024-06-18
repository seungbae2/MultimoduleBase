package com.sb.multimodule.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class InfoResponse(
    val totalPages: Int,
    val count: Int,
    val previousPage: String,
    val nextPage: String,
)