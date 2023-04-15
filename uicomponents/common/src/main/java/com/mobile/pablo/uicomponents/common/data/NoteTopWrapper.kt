package com.mobile.pablo.uicomponents.common.data

data class NoteTopWrapper(
    val onBackItem: () -> Unit,
    val onShareItem: () -> Unit,
    val onDoneItem: () -> Unit
)
