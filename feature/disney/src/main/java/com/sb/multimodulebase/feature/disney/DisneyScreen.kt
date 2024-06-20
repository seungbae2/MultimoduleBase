package com.sb.multimodulebase.feature.disney

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sb.multimodulebase.core.model.DisneyCharacter

@Composable
internal fun DisneyRoute(
    viewModel: DisneyViewModel = hiltViewModel(),
) {
    val disneyCharacterPagingItems: LazyPagingItems<DisneyCharacter> = 
        viewModel.disneyCharactersFlow.collectAsLazyPagingItems()
    DisneyScreen(disneyCharacterPagingItems)
}

@Composable
internal fun DisneyScreen(
    disneyCharacterPagingItems: LazyPagingItems<DisneyCharacter>,
) {
    LazyColumn {
        items(disneyCharacterPagingItems.itemCount) { index ->
            disneyCharacterPagingItems[index]?.let { character ->
                Text(text = character.name)
            }
        }
    }
}