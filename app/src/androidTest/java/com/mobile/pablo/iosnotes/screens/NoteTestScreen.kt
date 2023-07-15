package com.mobile.pablo.iosnotes.screens

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mobile.pablo.iosnotes.MainActivity
import com.mobile.pablo.iosnotes.ext.click
import com.mobile.pablo.uicomponents.note.R

class NoteTestScreen constructor(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) : BasicTestScreen(rule) {

    val views = NoteScreenViews

    fun clickAddItemBtn(): SemanticsNodeInteraction = onUnmergedTreeWithTag(views.addItemBtn).click()

    object NoteScreenViews {

        val addItemBtn = R.string.test_id_add_note_btn
    }
}
