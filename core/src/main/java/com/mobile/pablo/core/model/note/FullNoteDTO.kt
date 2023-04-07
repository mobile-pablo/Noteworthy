package com.mobile.pablo.core.model.note

import java.util.Date

data class FullNoteDTO(
    val id: String,
    val title: String,
    val date: Date,
    val fullDescription: List<NoteLineDTO?>
)
