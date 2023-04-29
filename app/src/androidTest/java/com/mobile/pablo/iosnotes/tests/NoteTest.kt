package com.mobile.pablo.iosnotes.tests

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
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
import javax.inject.Inject
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
    val testRule = createAndroidComposeRule<ComponentActivity>()

    @Inject
    lateinit var noteViewModel: NoteViewModel

    @Before
    fun setup() {
        hiltAndroidRule.inject()
        testRule.setContent {
            val navController = rememberNavController()
            noteViewModel = hiltViewModel()
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
