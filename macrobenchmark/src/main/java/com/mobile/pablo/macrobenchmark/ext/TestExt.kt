package com.mobile.pablo.macrobenchmark.ext

import com.mobile.pablo.macrobenchmark.screens.AddNoteBenchmarkScreen
import com.mobile.pablo.macrobenchmark.screens.NoteBenchmarkScreen

fun noteBenchmark(func: NoteBenchmarkScreen.() -> Unit) =
    NoteBenchmarkScreen().apply { func() }

fun addNoteBenchmark(func: AddNoteBenchmarkScreen.() -> Unit) =
    AddNoteBenchmarkScreen().apply { func() }
