package com.mobile.pablo.domain.data.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class PreviewNote(
    val id: Int? = null,
    val title: String,
    val date: Date,
    val description: String
) : Parcelable
