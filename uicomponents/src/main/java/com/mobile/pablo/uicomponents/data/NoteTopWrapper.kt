package com.mobile.pablo.uicomponents.data

data class NoteTopWrapper(
    val onBackItem : () -> Unit,
    val onShareItem : () -> Unit,
    val onDoneItem : () -> Unit,
)
