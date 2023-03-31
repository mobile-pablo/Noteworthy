package com.mobile.pablo.storage.mapper.note

import com.mobile.pablo.core.model.home.FullNoteDTO
import com.mobile.pablo.storage.database.entity.FullNoteEntity
import javax.inject.Inject

internal class FullNoteDTOMapper @Inject constructor() {

    fun map(entity: FullNoteEntity?): FullNoteDTO? = entity?.run {
        FullNoteDTO(
            id,
            title,
            date,
            fullDescription
        )
    }

    fun map(dto: FullNoteDTO?): FullNoteEntity? = dto?.run {
        FullNoteEntity(
            id,
            title,
            date,
            fullDescription
        )
    }
}