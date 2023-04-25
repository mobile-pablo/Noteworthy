package com.mobile.pablo.iosnotes.screens

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mobile.pablo.iosnotes.ext.click
import com.mobile.pablo.iosnotes.ext.getStringByAutomator
import com.mobile.pablo.uicomponents.note.R

class NoteTestScreen constructor(
    rule: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
) : BasicTestScreen(rule) {

    val views = NoteScreenViews

    fun clickAddItemBtn(): SemanticsNodeInteraction = onUnmergedTreeWithTag("add_note_btn").click()

    object NoteScreenViews {

        val addItemBtn = getStringByAutomator(R.string.test_id_add_note_btn)
    }
}
