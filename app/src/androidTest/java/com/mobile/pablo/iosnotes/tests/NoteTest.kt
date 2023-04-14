package com.mobile.pablo.iosnotes.tests

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mobile.pablo.note.mock.MOCK_NOTE_LIST
import com.mobile.pablo.note.mock.MockNoteScreen
import com.mobile.pablo.uicomponents.common.theme.IOSNotesTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            IOSNotesTheme {
                MockNoteScreen()
            }
        }
    }

    @Test
    fun noteListIsDisplayed() {
        composeTestRule.apply {
            MOCK_NOTE_LIST.withIndex().forEachIndexed { index, _ ->
                composeTestRule.onNodeWithTag("note-$index").assertIsDisplayed()
            }
        }
    }
}