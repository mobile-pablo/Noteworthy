package com.mobile.pablo.domain.data.note

import android.os.Parcelable
import com.mobile.pablo.core.utils.StringConst.EMPTY_STRING
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteLine(
    var id: Int = 0,
    val parentNoteId: Int = 0,
    val isCheckbox: Boolean = false,
    val noteText: String = EMPTY_STRING
) : Parcelable
