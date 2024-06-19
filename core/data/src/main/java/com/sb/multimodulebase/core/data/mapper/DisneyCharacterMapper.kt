package com.sb.multimodulebase.core.data.mapper

import com.sb.multimodule.core.network.model.DisneyCharacterResponse
import com.sb.multimodulebase.core.model.DisneyCharacter

internal fun DisneyCharacterResponse.toExternalData(): DisneyCharacter =
    DisneyCharacter(
        _id = _id,
        films = films,
        shortFilms = shortFilms,
        tvShows = tvShows,
        videoGames = videoGames,
        parkAttractions = parkAttractions,
        allies = allies,
        enemies = enemies,
        sourceUrl = sourceUrl,
        name = name,
        imageUrl = imageUrl,
        createdAt = createdAt,
        updatedAt = updatedAt,
        url = url,
        __v = __v
    )