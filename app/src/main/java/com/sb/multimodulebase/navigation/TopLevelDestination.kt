package com.sb.multimodulebase.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.sb.multimodulebase.R

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
){
    DISNEY(
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Filled.Home,
        iconTextId = R.string.feature_disney,
        titleTextId = R.string.feature_disney
    )
}