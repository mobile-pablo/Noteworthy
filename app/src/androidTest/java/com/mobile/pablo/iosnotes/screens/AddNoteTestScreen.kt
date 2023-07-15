package com.mobile.pablo.iosnotes.screens

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mobile.pablo.iosnotes.MainActivity
import com.mobile.pablo.addNote.R as Res

class AddNoteTestScreen constructor(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) : BasicTestScreen(rule) {

    val views = AddNoteScreenViews

    object AddNoteScreenViews {

        val addNoteScreen = Res.string.id_add_note_screen
    }
}
