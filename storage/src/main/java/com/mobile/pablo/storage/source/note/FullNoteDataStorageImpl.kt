package com.mobile.pablo.storage.source.note

import com.mobile.pablo.core.model.home.FullNoteDTO
import com.mobile.pablo.storage.database.dao.note.FullNoteDao
import com.mobile.pablo.storage.mapper.note.FullNoteDTOMapper
import javax.inject.Inject

internal class FullNoteDataStorageImpl @Inject constructor(
    private val fullNoteDao: FullNoteDao,
    private val fullNoteDTOMapper: FullNoteDTOMapper
) : FullNoteDataStorage {

    override suspend fun getNotes(): List<FullNoteDTO?> =
        fullNoteDao.getNotes().map(fullNoteDTOMapper::map)

    override suspend fun deleteNote(noteId: String): Unit = fullNoteDao.deleteNote(noteId)

    override suspend fun insertNote(fullNoteEntity: FullNoteDTO?) {
        val entity = fullNoteDTOMapper.map(fullNoteEntity)
        fullNoteDao.insertNote(entity)
    }

    override suspend fun clearNotes(): Unit = fullNoteDao.clearNotes()
}