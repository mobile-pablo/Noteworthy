package com.mobile.pablo.iosnotes.tests

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mobile.pablo.iosnotes.ext.isDisplayed
import com.mobile.pablo.iosnotes.screens.NoteScreen
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
    val testRule = createComposeRule()

    val noteScreen = NoteScreen(testRule)

    @Before
    fun setup() {
        testRule.setContent {
            IOSNotesTheme {
                MockNoteScreen()
            }
        }
    }

    @Test
    fun notesAreDisplayed() {
        testRule.apply {
            MOCK_NOTE_LIST.withIndex().forEachIndexed { index, _ ->
                testRule.onNodeWithTag("previewNote-$index").isDisplayed()
            }
        }
    }

    @Test
    fun itemNoteScreenIsOpened() {
        noteScreen.clickAddItemBtn()
    }

    @Test
    fun itemNoteIsRemoved() {}

    @Test
    fun itemNoteIsPinned() {}
}
