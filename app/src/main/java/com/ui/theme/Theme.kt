package com.KivoFit.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun KivoFitTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colorScheme = DarkColors,
            typography = AppTypography,
            shapes = AppShapes,
            content = content
        )
    }
}
