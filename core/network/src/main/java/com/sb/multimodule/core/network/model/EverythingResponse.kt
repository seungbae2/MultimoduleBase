package com.sb.multimodule.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class EverythingResponse(
    val articles: List<ArticleResponse>,
    val status: String,
    val totalResults: Int,
)