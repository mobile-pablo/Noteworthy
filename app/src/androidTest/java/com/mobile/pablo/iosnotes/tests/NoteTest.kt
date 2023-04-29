package com.mobile.pablo.iosnotes.tests

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import com.mobile.pablo.iosnotes.MainActivity
import com.mobile.pablo.iosnotes.ext.addNoteScreen
import com.mobile.pablo.iosnotes.ext.sleepView
import com.mobile.pablo.iosnotes.nav.NavGraphs
import com.mobile.pablo.iosnotes.screens.NoteTestScreen
import com.mobile.pablo.note.NoteScreen
import com.mobile.pablo.note.NoteViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.withIndex
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

    @Test
    fun notesAreDisplayed() {
        noteViewModel.notes.withIndex().map {
            it.value.forEach { note ->
                note?.let {
                    testRule.onNodeWithTag("previewNote-${it.id}").assertIsDisplayed()
                }
            }
        }
    }

    @Test
    fun itemNoteScreenIsOpened() {
        sleepView()
        NoteTestScreen(testRule).clickAddItemBtn()

        addNoteScreen(testRule) {
            waitForTag(views.addNoteScreen)
            onTag(views.addNoteScreen).assertIsDisplayed()
        }
    }
}
