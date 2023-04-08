package com.mobile.pablo.domain.mapper.note

import com.mobile.pablo.core.model.note.NoteDTO
import com.mobile.pablo.domain.data.note.Note
import javax.inject.Inject

class NoteMapper @Inject constructor(
   private val noteLineMapper: NoteLineMapper
) {

    fun map(dto: NoteDTO?): Note? = dto?.run {
        Note(
            id,
            title,
            date,
            description.map(noteLineMapper::map)
        )
    }

    fun map(note: Note?): NoteDTO? = note?.run {
        NoteDTO(
            id,
            title,
            date,
            description.map(noteLineMapper::map)
        )
    }
}