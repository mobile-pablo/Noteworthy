package com.mobile.pablo.macrobenchmark.test

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mobile.pablo.macrobenchmark.viewAssertions.addItemAndOpenList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class NoteBenchmarkTest {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startCompilationModeNone() = startCompilationModePartial(mode = CompilationMode.None())

    @Test
    fun startCompilationModePartial() = startCompilationModePartial(mode = CompilationMode.Partial())

    @Test
    fun addItemAndScrollListCompilationModeNone() = addItemAndOpenListCompilation(mode = CompilationMode.None())

    @Test
    fun addItemAndScrollListCompilationModePartial() = addItemAndOpenListCompilation(mode = CompilationMode.Partial())

    // Setup for the benchmark
    fun benchmarkSetup(
        mode: CompilationMode,
        func: () -> Unit = {}
    ) = benchmarkRule.measureRepeated(
        packageName = "com.mobile.pablo.iosnotes",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        compilationMode = mode
    ) {
        pressHome()
        startActivityAndWait()
        func()
    }

    fun startCompilationModePartial(mode: CompilationMode) = benchmarkSetup(mode = mode)

    fun addItemAndOpenListCompilation(mode: CompilationMode) = benchmarkSetup(mode = mode) {
        addItemAndOpenList()
    }
}
