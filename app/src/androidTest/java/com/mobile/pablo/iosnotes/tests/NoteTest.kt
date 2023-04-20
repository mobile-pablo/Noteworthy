package com.mobile.pablo.iosnotes.tests

import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.mobile.pablo.iosnotes.ext.isDisplayed
import com.mobile.pablo.iosnotes.ext.isNotDisplayed
import com.mobile.pablo.iosnotes.ext.sleepView
import com.mobile.pablo.iosnotes.screens.NoteTestScreen
import com.mobile.pablo.note.mock.FakeNoteScreen
import com.mobile.pablo.note.mock.FakeNoteViewModel
import com.mobile.pablo.uicomponents.common.theme.IOSNotesTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.withIndex
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NoteTest {

    @get:Rule(order = 0)
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val testRule = createAndroidComposeRule<ComponentActivity>()

    private val noteTestScreen get() = NoteTestScreen(testRule)

    private val fakeNoteViewModel = FakeNoteViewModel()

    @Before
    fun setup() {
        hiltAndroidRule.inject()
        testRule.setContent {
            IOSNotesTheme {
                FakeNoteScreen(fakeNoteViewModel = fakeNoteViewModel)
            }
        }
    }

    @Test
    fun notesAreDisplayed() {
        fakeNoteViewModel.notes.withIndex().map {
            it.value.forEachIndexed { index, note ->
                note?.let {
                    testRule.onNodeWithTag("previewNote-${it.id}").isDisplayed()
                }
            }
        }
    }

    @Test
    fun itemNoteScreenIsOpened() {
        sleepView()
        noteTestScreen.clickAddItemBtn()
    }
}
