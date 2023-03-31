package com.mobile.pablo.uicomponents.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.mobile.pablo.uicomponents.ui.util.MaterialTheme
import com.mobile.pablo.uicomponents.ui.util.darkCustomColors

private val darkColors = darkCustomColors(
    text = TextNight,
    homeBackground = HomeBackgroundNight,
    noteBackground = NoteBackgroundNight,
    topBarBackground = TopBarBackgroundNight,
    topBarText = TopBarTextNight,
    topBarSelectedItemBackground = TopBarSelectedItemBackgroundNight,
    topBarSelectedItemText = TopBarSelectedItemTextNight,
    selectedNoteBackground = SelectedNoteBackgroundNight
)

private val lightColors = darkCustomColors(
    text = TextDay,
    homeBackground = HomeBackgroundDay,
    noteBackground = NoteBackgroundDay,
    topBarBackground = TopBarBackgroundDay,
    topBarText = TopBarTextDay,
    topBarSelectedItemBackground = TopBarSelectedItemBackgroundDay,
    topBarSelectedItemText = TopBarSelectedItemTextDay,
    selectedNoteBackground = SelectedNoteBackgroundDay
)

@Composable
fun IOSNotesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val color = remember(darkTheme) {
        if (darkTheme) darkColors else lightColors
    }

    MaterialTheme(
        colors = color,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}