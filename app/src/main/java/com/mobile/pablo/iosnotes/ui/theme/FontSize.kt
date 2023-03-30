package com.mobile.pablo.iosnotes.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class FontSize(
    val default: TextUnit = 0.sp,
    val font_9: TextUnit = 9.sp,
    val font_11: TextUnit = 11.sp,
    val font_12: TextUnit = 12.sp,
    val font_14: TextUnit = 14.sp,
    val font_15: TextUnit = 15.sp,
    val font_16: TextUnit = 16.sp,
    val font_20: TextUnit = 20.sp,
    val font_23: TextUnit = 23.sp,
    val font_28: TextUnit = 28.sp,
    val font_34: TextUnit = 34.sp,
    val font_54: TextUnit = 54.sp
)

val LocalFontSize = compositionLocalOf { FontSize() }

val MaterialTheme.fontSize: FontSize
    @Composable
    @ReadOnlyComposable
    get() = LocalFontSize.current