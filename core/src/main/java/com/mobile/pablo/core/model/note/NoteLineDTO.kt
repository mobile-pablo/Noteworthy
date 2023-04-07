package com.mobile.pablo.core.model.note

data class NoteLineDTO(
    val id: String,
    val fullNoteId: String,
    val isCheckbox: Boolean = false,
    val noteText: String = ""
)
