package com.mobile.pablo.macrobenchmark.ext

import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import org.junit.Assert.assertEquals

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

fun getResByContainedString(string: String) = uiDevice.findObject(
    uiSelector.textContains(string)
)

fun assertText(uiObject: UiObject, text: String) {
    assertEquals(uiObject.text, text)
}
