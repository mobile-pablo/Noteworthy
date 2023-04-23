package com.mobile.pablo.uicomponents.common.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.material.MaterialTheme as Theme

typealias contentCompose = @Composable () -> Unit

@Composable
fun IOSNotesTheme(
    content: contentCompose
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
