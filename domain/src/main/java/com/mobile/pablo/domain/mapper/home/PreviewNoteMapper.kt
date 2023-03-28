package com.mobile.pablo.domain.mapper.home

import com.mobile.pablo.core.data.home.PreviewNoteDTO
import com.mobile.pablo.domain.data.home.PreviewNote
import javax.inject.Inject

class PreviewNoteMapper @Inject constructor() {

    fun map(dto: PreviewNoteDTO?): PreviewNote? = dto?.run {
        PreviewNote(
            id,
            title,
            date,
            description
        )
    }

    fun map(PreviewNote: PreviewNote?): PreviewNoteDTO? = PreviewNote?.run {
        PreviewNoteDTO(
            id,
            title,
            date,
            description
        )
    }
}