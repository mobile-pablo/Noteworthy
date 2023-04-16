package com.mobile.pablo.domain.data.note

import android.os.Parcelable
import com.mobile.pablo.core.utils.StringConst.EMPTY_STRING
import java.util.Date
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id: Int = 0,
    val title: String = EMPTY_STRING,
    val date: Date = Date(),
    val description: List<NoteLine> = listOf()
) : Parcelable
