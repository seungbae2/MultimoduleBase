package com.sb.multimodulebase.core.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class RecentSearchQuery(
    val query: String,
    val queriedDate: Instant = Clock.System.now(),
)
