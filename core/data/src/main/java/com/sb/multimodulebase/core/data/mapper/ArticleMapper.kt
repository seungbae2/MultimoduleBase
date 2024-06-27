package com.sb.multimodulebase.core.data.mapper

import com.sb.multimodule.core.network.model.ArticleResponse
import com.sb.multimodule.core.network.model.SourceResponse
import com.sb.multimodulebase.core.model.Article
import com.sb.multimodulebase.core.model.Source

internal fun ArticleResponse.toExternalData(): Article =
    Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source.toExternalData(),
        title= title,
        url = url,
        urlToImage = urlToImage,
    )

internal fun SourceResponse.toExternalData(): Source =
    Source(
        id = id,
        name = name,
    )