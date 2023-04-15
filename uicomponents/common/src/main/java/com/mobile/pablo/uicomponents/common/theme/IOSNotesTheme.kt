package com.mobile.pablo.uicomponents.common.theme

import androidx.compose.material.MaterialTheme as Theme
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
        Theme(
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}
