package com.mobile.pablo.macrobenchmark.baseline

import androidx.benchmark.macro.ExperimentalBaselineProfilesApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mobile.pablo.macrobenchmark.viewAssertions.addItemAndOpenList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalBaselineProfilesApi::class)
@RunWith(AndroidJUnit4::class)
class NoteBaselineProfileGenerator {

    @get:Rule
    val baseLineRule = BaselineProfileRule()

    @Test
    fun addItemAndOpenListGenerateBaseline() = baseLineRule.collectBaselineProfile(
        packageName = "com.mobile.pablo.iosnotes"
    ) {
        pressHome()
        startActivityAndWait()

        addItemAndOpenList()
    }
}
