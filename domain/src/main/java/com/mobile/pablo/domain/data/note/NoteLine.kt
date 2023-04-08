package com.mobile.pablo.domain.data.note

data class NoteLine(
    val id: Int,
    val parentNoteId: Int,
    val isCheckbox: Boolean = false,
    val noteText: String = ""
)
