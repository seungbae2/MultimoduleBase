package com.sb.multimodule.core.network.model


import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: Instant?,
    val source: SourceResponse,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)