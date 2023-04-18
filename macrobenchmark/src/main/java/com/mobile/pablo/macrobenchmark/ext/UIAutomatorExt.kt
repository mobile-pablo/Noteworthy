package com.mobile.pablo.macrobenchmark.ext

import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector

val instrumentation = InstrumentationRegistry.getInstrumentation()
val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)

val uiSelector = UiSelector()

fun getResByString(@StringRes resId: Int) = uiDevice.findObject(
    uiSelector.resourceId(instrumentation.context.getString(resId))
)

fun getResByContainedString(string: String) = uiDevice.findObject(
    uiSelector.textContains(string)
)
