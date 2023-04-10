package com.mobile.pablo.domain.data.note

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteLine(
    var id: Int = 0,
    val parentNoteId: Int = 0,
    val isCheckbox: Boolean = false,
    val noteText: String = ""
) : Parcelable
