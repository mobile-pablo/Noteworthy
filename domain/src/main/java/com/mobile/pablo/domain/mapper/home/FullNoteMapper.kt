package com.mobile.pablo.domain.mapper.home

import com.mobile.pablo.core.data.home.FullNoteDTO
import com.mobile.pablo.domain.data.home.FullNote
import javax.inject.Inject

class FullNoteMapper @Inject constructor() {

    fun map(dto: FullNoteDTO?): FullNote? = dto?.run {
        FullNote(
            id,
            title,
            date,
            fullDescription
        )
    }

    fun map(fullNote: FullNote?): FullNoteDTO? = fullNote?.run {
        FullNoteDTO(
            id,
            title,
            date,
            fullDescription
        )
    }
}