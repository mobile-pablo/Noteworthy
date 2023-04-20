package com.mobile.pablo.iosnotes.ext

import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector

val instrumentation = InstrumentationRegistry.getInstrumentation()
val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)

val uiSelector = UiSelector()

fun getString(@StringRes resId: Int) = instrumentation.context.getString(resId)

fun getResByString(@StringRes resId: Int) = uiDevice.findObject(
    uiSelector.resourceId(getString(resId))
)

fun getResFromId(id: String) = uiDevice.findObject(
    uiSelector.resourceId(id)
)

fun swipeLeft(string: String) = getResFromId(string).swipeLeft(100)

fun swipeRight(string: String) = getResFromId(string).swipeRight(100)
