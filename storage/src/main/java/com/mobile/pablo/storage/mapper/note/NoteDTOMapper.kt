package com.mobile.pablo.storage.mapper.note

import com.mobile.pablo.core.model.note.NoteDTO
import com.mobile.pablo.storage.database.entity.NoteEntity
import com.mobile.pablo.storage.database.entity.NoteLineEntity
import javax.inject.Inject

internal class NoteDTOMapper @Inject constructor(
    private val noteLineDTOMapper: NoteLineDTOMapper
) {

    fun map(
        entity: NoteEntity?,
        description: List<NoteLineEntity>?
    ): NoteDTO? = entity?.run {
        NoteDTO(
            id,
            title,
            date,
            description?.map(noteLineDTOMapper::map)
        )
    }

    fun map(dto: NoteDTO?): NoteEntity? = dto?.run {
        NoteEntity(
            id,
            title,
            date,
        )
    }
}