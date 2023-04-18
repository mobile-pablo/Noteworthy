package com.mobile.pablo.macrobenchmark.screens

import com.mobile.pablo.uicomponents.note.R
import com.mobile.pablo.macrobenchmark.ext.getResByString

class NoteBenchmarkScreen {

    val views = NoteBenchmarkScreenViews

    fun clickAddItemBtn() {
        views.addItemBtn.click()
    }

    object NoteBenchmarkScreenViews {

        val addItemBtn = getResByString(R.string.test_id_add_note_btn)
    }
}
