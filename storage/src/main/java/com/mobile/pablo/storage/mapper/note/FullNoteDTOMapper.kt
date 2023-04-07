package com.mobile.pablo.storage.mapper.note

import com.mobile.pablo.core.model.note.FullNoteDTO
import com.mobile.pablo.storage.database.entity.FullNoteEntity
import com.mobile.pablo.storage.database.entity.NoteLineEntity
import javax.inject.Inject

internal class FullNoteDTOMapper @Inject constructor(
    private val noteLineDTOMapper: NoteLineDTOMapper
) {

    fun map(entity: FullNoteEntity?, description: List<NoteLineEntity>?): FullNoteDTO? = entity?.run {
        FullNoteDTO(
            id,
            title,
            date,
            description?.map(noteLineDTOMapper::map)
        )
    }

    fun map(dto: FullNoteDTO?): FullNoteEntity? = dto?.run {
        FullNoteEntity(
            id,
            title,
            date,
        )
    }
}