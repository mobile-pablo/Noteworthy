package com.mobile.pablo.uicomponents.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Day
val TextDay = Color(0xff271e05)
val NoteBackgroundDay = Color(0xff292929)
val HomeBackgroundDay = Color(0xfff2f2f4)
val SearchDay = Color(0xffe4e3e9)
val PreviewNoteBackgroundDay = Color(0xffffffff)
val PreviewLineDay = Color(0xffededed)
val CTADay = Color(0xffd8b86a)
val HomeBottomBarBackgroundDay = Color(0xfff6f6f6)


// Night
val TextNight = Color(0xfff2eadf)
val NoteBackgroundNight = Color(0xff1f1f1f)
val HomeBackgroundNight = Color(0xff2d271b)
val SearchNight = Color(0xff1c1c1e)
val PreviewNoteBackgroundNight = Color(0xff1c1c1e)
val PreviewLineNight  = Color(0xff252527)
val CTANight = Color(0xffd6c04c)
val HomeBottomBarBackgroundNight = Color(0xff29292b)


val Colors.Text: Color @Composable
    get() = if (isSystemInDarkTheme()) TextNight else TextDay

val Colors.NoteBackground: Color @Composable
    get() = if (isSystemInDarkTheme()) NoteBackgroundNight else NoteBackgroundDay

val Colors.HomeBackground: Color @Composable
    get() = if (isSystemInDarkTheme()) HomeBackgroundNight else HomeBackgroundDay

val Colors.SearchBackground: Color @Composable
    get() = if (isSystemInDarkTheme()) SearchNight else SearchDay

val Colors.PreviewNoteBackground: Color @Composable
    get() = if (isSystemInDarkTheme()) PreviewNoteBackgroundNight else PreviewNoteBackgroundDay

val Colors.PreviewLine: Color @Composable
    get() = if (isSystemInDarkTheme()) PreviewLineNight else PreviewLineDay

val Colors.CTA: Color @Composable
    get() = if (isSystemInDarkTheme()) CTANight else CTADay

val Colors.HomeBottomBarBackground: Color @Composable
    get() = if (isSystemInDarkTheme()) HomeBottomBarBackgroundNight else HomeBottomBarBackgroundDay
