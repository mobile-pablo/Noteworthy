package com.mobile.pablo.storage.source.note

import com.mobile.pablo.core.model.note.FullNoteDTO
import com.mobile.pablo.storage.database.dao.note.FullNoteDao
import com.mobile.pablo.storage.database.entity.FullNoteEntity
import com.mobile.pablo.storage.database.entity.FullNoteWithDescriptionEntity
import com.mobile.pablo.storage.mapper.note.FullNoteDTOMapper
import com.mobile.pablo.storage.mapper.note.NoteLineDTOMapper
import java.util.Date
import javax.inject.Inject

internal class FullNoteDataStorageImpl @Inject constructor(
    private val fullNoteDao: FullNoteDao,
    private val fullNoteDTOMapper: FullNoteDTOMapper,
    private val noteLineDTOMapper: NoteLineDTOMapper
) : FullNoteDataStorage {

    override suspend fun getNote(noteId: String): FullNoteDTO? {
        val dao = fullNoteDao.getNotesWithDescriptions(noteId)
        return fullNoteDTOMapper.map(
                dao.fullNoteEntity,
                dao.noteLineEntityList
            )
    }

    override suspend fun deleteNote(noteId: String): Unit = fullNoteDao.deleteNoteWithDescription(noteId)

    override suspend fun insertNote(dto: FullNoteDTO?) {
        val entity = fullNoteDTOMapper.map(dto)
        val description = dto?.fullDescription!!.map(noteLineDTOMapper::map)

        fullNoteDao.insertNoteWithDescription(
            FullNoteWithDescriptionEntity(
                entity,
                description
            )
        )
    }

    override suspend fun clearNotes(): Unit = fullNoteDao.clearNotesWithDescriptions()
}