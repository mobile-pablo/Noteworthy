package com.mobile.pablo.iosnotes.ext

import android.app.Instrumentation
import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObjectNotFoundException
import androidx.test.uiautomator.UiSelector

val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation()
val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)

val uiSelector = UiSelector()

fun getString(@StringRes resId: Int) = instrumentation.context.getString(resId)

fun getResByString(@StringRes resId: Int) = uiDevice.findObject(
    uiSelector.resourceId(getString(resId))
)

fun getResFromId(id: String) = uiDevice.findObject(
    uiSelector.resourceId(id)
)

fun swipeLeft(
    string: String
) {
    try {
        val uiObject = getResFromId(string)
        val objBounds = uiObject.visibleBounds
        val startX = objBounds.centerX()
        val endX = startX - (objBounds.width() * 0.75).toInt()
        val centerY = objBounds.centerY()
        uiDevice.swipe(
            startX,
            centerY,
            endX,
            centerY,
            10
        )
    } catch (e: UiObjectNotFoundException) {
        e.printStackTrace()
    }
}

fun swipeRight(
    string: String
) {
    try {
        val uiObject = getResFromId(string)
        val objBounds = uiObject.visibleBounds
        val startX = objBounds.centerX()
        val endX = startX + (objBounds.width() * 0.75).toInt()
        val centerY = objBounds.centerY()
        uiDevice.swipe(
            startX,
            centerY,
            endX,
            centerY,
            10
        )
    } catch (e: UiObjectNotFoundException) {
        e.printStackTrace()
    }
}
