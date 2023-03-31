package com.mobile.pablo.core.model.home

import java.util.Date

data class FullNoteDTO(
    val id: Int,
    val title: String,
    val date: Date,
    val fullDescription: String
)
