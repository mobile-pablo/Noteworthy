package com.mobile.pablo.uicomponents.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun IOSNotesTheme(
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalFontSize provides FontSize()
    ) {
        MaterialTheme(
            typography = Typography,
            shapes = Shapes,
            content = content,
        )
    }
}