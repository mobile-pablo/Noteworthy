package com.mobile.pablo.domain.data.note

import java.util.Date

data class FullNote(
    val id: String,
    val title: String,
    val date: Date,
    val fullDescription: List<NoteLine?>
)