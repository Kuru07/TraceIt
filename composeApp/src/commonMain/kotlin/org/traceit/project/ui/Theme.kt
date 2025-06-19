package org.traceit.project.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColors = lightColorScheme(
    primary = Color(0xFFFFD691),
    onPrimary = Color(0xFF233A66),
    secondary = Color(0xFFFFD691),
    onSecondary = Color.White,
    background = Color(0xFF233A66),
    onBackground = Color.White,
    surface = Color(0xFF233A66),
    onSurface = Color.White
)

@Composable
fun TraceItTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content
    )
}