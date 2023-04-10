package com.mobile.pablo.domain.data.note

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Note(
    val id: Int,
    val title: String,
    val date: Date,
    val description: List<NoteLine>?
) : Parcelable
