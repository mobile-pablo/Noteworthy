package com.mobile.pablo.storage.mapper.home

import com.mobile.pablo.core.model.home.PreviewNoteDTO
import com.mobile.pablo.storage.database.entity.PreviewNoteEntity
import javax.inject.Inject

internal class PreviewNoteDTOMapper @Inject constructor() {

    fun map(entity: PreviewNoteEntity?): PreviewNoteDTO? = entity?.run {
        PreviewNoteDTO(
            id,
            title,
            date,
            description
        )
    }

    fun map(dto: PreviewNoteDTO?): PreviewNoteEntity? = dto?.run {
        PreviewNoteEntity(
            id,
            title,
            date,
            description
        )
    }
}