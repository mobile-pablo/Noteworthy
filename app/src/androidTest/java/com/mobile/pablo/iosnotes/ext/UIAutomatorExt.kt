package com.mobile.pablo.iosnotes.ext

import android.app.Instrumentation
import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector

val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation()
val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)

val uiSelector = UiSelector()

fun getStringByAutomator(@StringRes resId: Int) = instrumentation.context.getString(resId)

fun getResByString(@StringRes resId: Int) = uiDevice.findObject(
    uiSelector.resourceId(getStringByAutomator(resId))
)

fun getResFromId(id: String) = uiDevice.findObject(
    uiSelector.resourceId(id)
)
