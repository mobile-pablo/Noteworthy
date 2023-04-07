package com.mobile.pablo.storage.source.note

import com.mobile.pablo.core.model.note.FullNoteDTO
import com.mobile.pablo.storage.database.dao.note.FullNoteDao
import com.mobile.pablo.storage.database.entity.FullNoteWithDescriptionEntity
import com.mobile.pablo.storage.mapper.note.FullNoteDTOMapper
import com.mobile.pablo.storage.mapper.note.NoteLineDTOMapper
import javax.inject.Inject

internal class FullNoteDataStorageImpl @Inject constructor(
    private val fullNoteDao: FullNoteDao,
    private val fullNoteDTOMapper: FullNoteDTOMapper,
    private val noteLineDTOMapper: NoteLineDTOMapper
) : FullNoteDataStorage {

    override suspend fun getNotes(): List<FullNoteDTO?> {
        val dao = fullNoteDao.getNotes()
        return dao.map {
            fullNoteDTOMapper.map(
                it?.fullNoteEntity,
                it?.noteLineEntityList
            )
        }
    }

    override suspend fun deleteNote(noteId: String): Unit = fullNoteDao.deleteNote(noteId)

    override suspend fun insertNote(fullNoteEntity: FullNoteDTO?) {
        val entity = fullNoteDTOMapper.map(fullNoteEntity)
        val description = fullNoteEntity?.fullDescription?.map(noteLineDTOMapper::map)

        val entityId = fullNoteDao.insertFullNote(entity)
        description?.forEach {
            val noteLineId = fullNoteDao.insertNoteLine(it)
            fullNoteDao.insertNote(
                FullNoteWithDescriptionEntity(
                    entityId,
                    noteLineId
                )
            )
        }

    }

    override suspend fun clearNotes(): Unit = fullNoteDao.clearNotes()
}