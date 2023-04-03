package com.mobile.pablo.uicomponents.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.material.MaterialTheme as Theme

@Composable
fun IOSNotesTheme(
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalFontSize provides FontSize()
    ) {
        Theme(
            typography = Typography,
            shapes = Shapes,
            content = content,
        )
    }
}