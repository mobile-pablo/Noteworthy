package com.mobile.pablo.domain.data.note

import android.os.Parcelable
import java.util.Date
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id: Int,
    val title: String,
    val date: Date,
    val description: List<NoteLine>?
) : Parcelable
