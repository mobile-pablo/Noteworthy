package com.mobile.pablo.iosnotes.ui.util

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Stable
class CustomColors(
    text: Color,
    noteBackground: Color,
    homeBackground: Color,
    topBarBackground: Color,
    topBarText: Color,
    topBarSelectedItemBackground: Color,
    topBarSelectedItemText: Color,
    selectedNoteBackground: Color,
    isLight: Boolean,
    defaultColor: Color = Color.White
) : Colors(
    text,
    noteBackground,
    homeBackground,
    topBarBackground,
    topBarText,
    topBarSelectedItemBackground,
    topBarSelectedItemText,
    defaultColor,
    defaultColor,
    defaultColor,
    defaultColor,
    defaultColor,
    isLight
) {

    var text by mutableStateOf(
        text,
        structuralEqualityPolicy()
    )
        internal set
    var noteBackground by mutableStateOf(
        noteBackground,
        structuralEqualityPolicy()
    )
        internal set
    var homeBackground by mutableStateOf(
        homeBackground,
        structuralEqualityPolicy()
    )
        internal set
    var topBarBackground by mutableStateOf(
        topBarBackground,
        structuralEqualityPolicy()
    )
        internal set
    var topBarText by mutableStateOf(
        topBarText,
        structuralEqualityPolicy()
    )
        internal set
    var topBarSelectedItemBackground by mutableStateOf(
        topBarSelectedItemBackground,
        structuralEqualityPolicy()
    )
        internal set
    var topBarSelectedItemText by mutableStateOf(
        topBarSelectedItemText,
        structuralEqualityPolicy()
    )
        internal set
    var selectedNoteBackground by mutableStateOf(
        selectedNoteBackground,
        structuralEqualityPolicy()
    )
        internal set
    override var isLight by mutableStateOf(
        isLight,
        structuralEqualityPolicy()
    )
        internal set

    /**
     * Returns a copy of this CustomColors, optionally overriding some of the values.
     */
    fun copy(
        primary: Color = this.text,
        primaryVariant: Color = this.noteBackground,
        secondary: Color = this.homeBackground,
        secondaryVariant: Color = this.topBarBackground,
        background: Color = this.topBarText,
        surface: Color = this.topBarSelectedItemBackground,
        error: Color = this.topBarSelectedItemText,
        onPrimary: Color = this.selectedNoteBackground,
        isLight: Boolean = this.isLight
    ): CustomColors = CustomColors(
        primary,
        primaryVariant,
        secondary,
        secondaryVariant,
        background,
        surface,
        error,
        onPrimary,
        isLight
    )

    override fun toString(): String {
        return "CustomColors(" +
            "primary=$text, " +
            "primaryVariant=$noteBackground, " +
            "secondary=$homeBackground, " +
            "secondaryVariant=$topBarBackground, " +
            "background=$topBarText, " +
            "surface=$topBarSelectedItemBackground, " +
            "error=$topBarSelectedItemText, " +
            "onPrimary=$selectedNoteBackground, " +
            "isLight=$isLight" +
            ")"
    }
}

fun lightCustomColors(
    text: Color = Color(0xFF6200EE),
    noteBackground: Color = Color(0xFF3700B3),
    homeBackground: Color = Color(0xFF03DAC6),
    topBarBackground: Color = Color(0xFF018786),
    topBarText: Color = Color.White,
    topBarSelectedItemBackground: Color = Color.White,
    topBarSelectedItemText: Color = Color(0xFFB00020),
    selectedNoteBackground: Color = Color.White,
): CustomColors = CustomColors(
    text,
    noteBackground,
    homeBackground,
    topBarBackground,
    topBarText,
    topBarSelectedItemBackground,
    topBarSelectedItemText,
    selectedNoteBackground,
    true
)

fun darkCustomColors(
    text: Color = Color(0xFFBB86FC),
    noteBackground: Color = Color(0xFF3700B3),
    homeBackground: Color = Color(0xFF03DAC6),
    topBarBackground: Color = homeBackground,
    topBarText: Color = Color(0xFF121212),
    topBarSelectedItemBackground: Color = Color(0xFF121212),
    topBarSelectedItemText: Color = Color(0xFFCF6679),
    selectedNoteBackground: Color = Color.Black,
) = CustomColors(
    text,
    noteBackground,
    homeBackground,
    topBarBackground,
    topBarText,
    topBarSelectedItemBackground,
    topBarSelectedItemText,
    selectedNoteBackground,
    false
)

internal val LocalColors = staticCompositionLocalOf { lightCustomColors() }

internal fun CustomColors.updateColorsFrom(other: CustomColors) {
    text = other.text
    noteBackground = other.noteBackground
    homeBackground = other.homeBackground
    topBarBackground = other.topBarBackground
    topBarText = other.topBarText
    topBarSelectedItemBackground = other.topBarSelectedItemBackground
    topBarSelectedItemText = other.topBarSelectedItemText
    selectedNoteBackground = other.selectedNoteBackground
    onPrimary = other.onPrimary
    onSecondary = other.onSecondary
    onBackground = other.onBackground
    onSurface = other.onSurface
    onError = other.onError
    isLight = other.isLight
}