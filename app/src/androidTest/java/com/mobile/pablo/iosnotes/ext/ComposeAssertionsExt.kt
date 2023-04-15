package com.mobile.pablo.iosnotes.ext

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed

fun SemanticsNodeInteraction.isNotDisplayed(): SemanticsNodeInteraction = assertIsNotDisplayed()
fun SemanticsNodeInteraction.isDisplayed(): SemanticsNodeInteraction = assertIsDisplayed()
