package com.mobile.pablo.macrobenchmark.viewAssertions

import com.mobile.pablo.macrobenchmark.ext.addNoteBenchmark
import com.mobile.pablo.macrobenchmark.ext.assertText
import com.mobile.pablo.macrobenchmark.screens.NoteBenchmarkScreen

fun addItemAndOpenList() {
    NoteBenchmarkScreen().clickAddItemBtn()
    addNoteBenchmark {
        assertText(
            views.title,
            "Title"
        )
        clickNoteLineAtPosition(0)
    }
}
