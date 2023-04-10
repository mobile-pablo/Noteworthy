package com.mobile.pablo.common.theme

import androidx.compose.material.MaterialTheme as Theme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp = 0.dp,
    val spacing_1: Dp = 1.dp,
    val spacing_2: Dp = 2.dp,
    val spacing_4: Dp = 4.dp,
    val spacing_6: Dp = 6.dp,
    val spacing_7: Dp = 7.dp,
    val spacing_8: Dp = 8.dp,
    val spacing_10: Dp = 10.dp,
    val spacing_12: Dp = 12.dp,
    val spacing_14: Dp = 14.dp,
    val spacing_16: Dp = 16.dp,
    val spacing_20: Dp = 20.dp,
    val spacing_24: Dp = 24.dp,
    val spacing_32: Dp = 32.dp,
    val spacing_40: Dp = 40.dp,
    val spacing_48: Dp = 48.dp,
    val spacing_56: Dp = 56.dp,
    val spacing_64: Dp = 64.dp,
    val spacing_82: Dp = 82.dp,
    val spacing_128: Dp = 128.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }

val Theme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current