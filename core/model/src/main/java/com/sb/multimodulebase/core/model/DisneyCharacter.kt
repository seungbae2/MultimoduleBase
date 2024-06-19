package com.sb.multimodulebase.core.model

import kotlinx.datetime.Instant

data class DisneyCharacter(
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
