package com.mobile.pablo.iosnotes.ext

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule

fun AndroidComposeTestRule<
    ActivityScenarioRule<ComponentActivity>, ComponentActivity>
    .getStringByAutomator(@StringRes resId: Int) = activity.getString(resId)
