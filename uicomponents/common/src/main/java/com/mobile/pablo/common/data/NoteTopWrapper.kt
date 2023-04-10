package com.mobile.pablo.common.data

data class NoteTopWrapper(
    val onBackItem : () -> Unit,
    val onShareItem : () -> Unit,
    val onDoneItem : () -> Unit,
)
