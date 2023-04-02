package com.mobile.pablo.uicomponents.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Day
val TextDay = Color(0xff271e05)
val NoteBackgroundDay = Color(0xff292929)
val HomeBackgroundDay = Color(0xffe3e3e5)
val TopBarBackgroundDay = Color(0xff3a3b3d)
val TopBarTextDay = Color(0xffa5a5a5)
val TopBarSelectedItemBackgroundDay = Color(0xffe7e8ea)
val TopBarSelectedItemTextDay = Color(0xff666769)
val SelectedNoteBackgroundDay = Color(0xffffe591)

// Night
val TextNight = Color(0xfff2eadf)
val NoteBackgroundNight = Color(0xff1f1f1f)
val HomeBackgroundNight = Color(0xff2d271b)
val TopBarBackgroundNight = Color(0xff3a3937)
val TopBarTextNight = Color(0xffb8b7b5)
val TopBarSelectedItemBackgroundNight = Color(0xff47484a)
val TopBarSelectedItemTextNight = Color(0xffb1b2b4)
val SelectedNoteBackgroundNight = Color(0xffa0852c)



val Colors.Text: Color @Composable
    get() = if (isSystemInDarkTheme()) TextNight else TextDay

val Colors.NoteBackground: Color @Composable
    get() = if (isSystemInDarkTheme()) NoteBackgroundNight else NoteBackgroundDay

val Colors.HomeBackground: Color @Composable
    get() = if (isSystemInDarkTheme()) HomeBackgroundNight else HomeBackgroundDay

val Colors.TopBarBackground: Color @Composable
    get() = if (isSystemInDarkTheme()) TopBarBackgroundNight else TopBarBackgroundDay

val Colors.TopBarText: Color @Composable
    get() = if (isSystemInDarkTheme()) TopBarTextNight else TopBarTextDay

val Colors.TopBarSelectedItemBackground: Color @Composable
    get() = if (isSystemInDarkTheme()) TopBarSelectedItemBackgroundNight else TopBarSelectedItemBackgroundDay

val Colors.TopBarSelectedItemText: Color @Composable
    get() = if (isSystemInDarkTheme()) TopBarSelectedItemTextNight else TopBarSelectedItemTextDay

val Colors.SelectedNoteBackground: Color @Composable
    get() = if (isSystemInDarkTheme()) SelectedNoteBackgroundNight else SelectedNoteBackgroundDay