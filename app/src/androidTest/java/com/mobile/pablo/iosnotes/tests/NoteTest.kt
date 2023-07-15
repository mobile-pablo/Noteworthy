package com.mobile.pablo.iosnotes.tests

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.iosnotes.MainActivity
import com.mobile.pablo.iosnotes.ext.addNoteScreen
import com.mobile.pablo.iosnotes.ext.isDisplayed
import com.mobile.pablo.iosnotes.ext.noteTestScreen
import com.mobile.pablo.iosnotes.nav.NavGraphs
import com.mobile.pablo.iosnotes.screens.NoteTestScreen
import com.mobile.pablo.note.NoteScreen
import com.mobile.pablo.note.NoteViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NoteTest {

    @get:Rule(order = 1)
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val testRule = createAndroidComposeRule<MainActivity>()

    lateinit var noteViewModel: NoteViewModel

    @Before
    fun setup() {
        hiltAndroidRule.inject()
        noteViewModel = testRule.activity.viewModels<NoteViewModel>().value
        testRule.activity.setContent {
            val navController = rememberNavController()
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                DestinationsNavHost(
                    navController = navController,
                    navGraph = NavGraphs.root
                )
                NoteScreen(
                    destinationsNavigator = EmptyDestinationsNavigator,
                    viewModel = noteViewModel
                )
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun notesAreDisplayed() = runTest {
        noteViewModel.notes.collectLatest {
            assertThat(it).isNotEmpty()
            it.forEach { note ->
                note?.let {
                    val tag = "previewNote-${note.id}"
                    noteTestScreen(testRule) {
                        waitForTag(tag)
                        onTag(tag).isDisplayed()
                    }
                }
            }
        }
    }

    @Test
    fun itemNoteScreenIsOpened() {
        NoteTestScreen(testRule).clickAddItemBtn()

        addNoteScreen(testRule) {
            waitForTag(views.addNoteScreen)
            onTag(views.addNoteScreen).isDisplayed()
        }
    }
}
