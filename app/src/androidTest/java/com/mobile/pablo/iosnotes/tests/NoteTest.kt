package com.mobile.pablo.iosnotes.tests

import androidx.compose.material.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.mobile.pablo.uicomponents.common.theme.IOSNotesTheme
import org.junit.Rule
import org.junit.Test

class NoteTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTest() {
        composeTestRule.setContent {
            IOSNotesTheme {
                Text(text = "Hi")
            }
        }
        composeTestRule.onNodeWithText("Hi").assertIsDisplayed()
    }
}