package com.sb.multimodule.core.network.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class DisneyCharacterResponse(
    val _id: Int,
    val films: List<String>,
    val shortFilms: List<String>,
    val tvShows: List<String>,
    val videoGames: List<String>,
    val parkAttractions: List<String>,
    val allies: List<String>,
    val enemies: List<String>,
    val sourceUrl: String,
    val name: String,
    val imageUrl: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val url: String,
    val __v: Int,
)
