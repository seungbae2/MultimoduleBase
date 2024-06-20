package com.sb.multimodulebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.sb.multimodulebase.core.designsystem.theme.MultimoduleBaseTheme
import com.sb.multimodulebase.ui.MainScreen
import com.sb.multimodulebase.ui.rememberAppState
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberAppState(
                windowSizeClass = calculateWindowSizeClass(this)
            )
            MultimoduleBaseTheme {
                MainScreen(appState = appState)
            }
        }
    }
}