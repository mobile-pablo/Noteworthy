package com.mobile.pablo.iosnotes.ext

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mobile.pablo.iosnotes.screens.AddNoteTestScreen
import com.mobile.pablo.iosnotes.screens.NoteTestScreen

inline fun noteScreen(
    rule: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>,
    crossinline func: NoteTestScreen.() -> Unit
) = NoteTestScreen(rule).apply { func() }

inline fun addNoteScreen(
    rule: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>,
    crossinline func: AddNoteTestScreen.() -> Unit
) = AddNoteTestScreen(rule).apply { func() }
