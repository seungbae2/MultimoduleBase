package com.sb.multimodulebase.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sb.multimodulebase.core.designsystem.component.BaseTopAppBar
import com.sb.multimodulebase.core.designsystem.icon.BaseIcons
import com.sb.multimodulebase.navigation.BaseBackground
import com.sb.multimodulebase.navigation.BaseNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    appState: AppState,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    BaseBackground(modifier = modifier) {
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal,
                        ),
                    ),
            ) {
                val destination = appState.currentTopLevelDestination
                val shouldShowTopAppBar = destination != null
                if (destination != null) {
                    BaseTopAppBar(
                        titleRes = destination.titleTextId,
                        navigationIcon = BaseIcons.Search,
                        navigationIconContentDescription = "Search",
                        actionIcon = BaseIcons.Settings,
                        actionIconContentDescription = "Settings",
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Transparent,
                        ),
                        onActionClick = {  },
                        onNavigationClick = {  },
                    )
                }

                Box(
                    modifier = if (shouldShowTopAppBar) {
                        Modifier.consumeWindowInsets(
                            WindowInsets.safeDrawing.only(WindowInsetsSides.Top),
                        )
                    } else {
                        Modifier
                    },
                ) {
                    BaseNavHost(
                        appState = appState,
                        onShowSnackbar = { message, action ->
                            snackbarHostState.showSnackbar(
                                message = message,
                                actionLabel = action,
                                duration = SnackbarDuration.Short,
                            ) == SnackbarResult.ActionPerformed
                        },
                    )
                }
            }
        }
    }
}