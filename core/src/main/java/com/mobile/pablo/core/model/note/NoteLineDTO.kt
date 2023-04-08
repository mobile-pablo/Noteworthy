package com.mobile.pablo.core.model.note

data class NoteLineDTO(
    val id: Int,
    val parentNoteId: Int,
    val isCheckbox: Boolean = false,
    val noteText: String = ""
)
