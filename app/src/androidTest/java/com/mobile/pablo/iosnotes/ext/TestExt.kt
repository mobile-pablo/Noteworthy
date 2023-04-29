package com.mobile.pablo.iosnotes.ext

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mobile.pablo.iosnotes.MainActivity
import com.mobile.pablo.iosnotes.screens.AddNoteTestScreen
import com.mobile.pablo.iosnotes.screens.NoteTestScreen

inline fun noteTestScreen(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    crossinline func: NoteTestScreen.() -> Unit
) = NoteTestScreen(rule).apply { func() }

inline fun addNoteScreen(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    crossinline func: AddNoteTestScreen.() -> Unit
) = AddNoteTestScreen(rule).apply { func() }
