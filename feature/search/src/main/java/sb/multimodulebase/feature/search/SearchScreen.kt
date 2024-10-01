package sb.multimodulebase.feature.search

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun SearchRoute(
    onBackClick: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    SearchScreen()
}

@Composable
internal fun SearchScreen() {
}