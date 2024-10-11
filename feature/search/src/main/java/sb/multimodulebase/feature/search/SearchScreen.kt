package sb.multimodulebase.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sb.multimodulebase.core.designsystem.icon.BaseIcons

@Composable
internal fun SearchRoute(
    onBackClick: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val searchResultUiState by viewModel.searchResultUiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    SearchScreen(
        searchQuery = searchQuery,
        searchResultUiState = searchResultUiState,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onBackClick = onBackClick,
    )
}

@Composable
internal fun SearchScreen(
    searchQuery: String = "",
    searchResultUiState: SearchResultUiState = SearchResultUiState.Loading,
    onSearchQueryChanged: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    Column {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        SearchToolbar(
            searchQuery = searchQuery,
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = {},
            onBackClick = onBackClick,
        )
    }
    when (searchResultUiState) {
        SearchResultUiState.Loading,
        SearchResultUiState.LoadFailed,
            -> Unit

        is SearchResultUiState.Success -> {
            if (searchResultUiState.isEmpty()) {
                Text("Empty result")
            } else {
                LazyColumn {
                    items(searchResultUiState.articles.size) { index ->
                        searchResultUiState.articles[index].let { article ->
                            article.title?.let { Text(it) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchToolbar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = BaseIcons.ArrowBack,
                contentDescription = "back",
            )
        }
        SearchTextField(
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered,
            searchQuery = searchQuery,
        )
    }
}

@Composable
private fun SearchTextField(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val onSearchExplicitlyTriggered = {
        keyboardController?.hide()
        onSearchTriggered(searchQuery)
    }

    TextField(
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onSearchQueryChanged("")
                    },
                ) {
                    Icon(
                        imageVector = BaseIcons.Close,
                        contentDescription = "clear search text",
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        onValueChange = {
            if ("\n" !in it) onSearchQueryChanged(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .focusRequester(focusRequester)
            .onKeyEvent {
                if (it.key == Key.Enter) {
                    onSearchExplicitlyTriggered()
                    true
                } else {
                    false
                }
            },
        shape = RoundedCornerShape(32.dp),
        value = searchQuery,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchExplicitlyTriggered()
            },
        ),
        maxLines = 1,
        singleLine = true,
    )
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}