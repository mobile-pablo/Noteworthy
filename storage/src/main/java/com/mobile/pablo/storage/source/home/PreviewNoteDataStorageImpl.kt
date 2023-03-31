package com.mobile.pablo.storage.source.home

import com.mobile.pablo.core.model.home.PreviewNoteDTO
import com.mobile.pablo.storage.database.dao.home.PreviewNoteDao
import com.mobile.pablo.storage.mapper.home.PreviewNoteDTOMapper
import javax.inject.Inject

internal class PreviewNoteDataStorageImpl @Inject constructor(
    private val previewNoteDao: PreviewNoteDao,
    private val previewNoteDTOMapper: PreviewNoteDTOMapper
) : PreviewNoteDataStorage {

    override suspend fun getNotes(): List<PreviewNoteDTO?> =
        previewNoteDao.getNotes().map(previewNoteDTOMapper::map)

    override suspend fun deleteNote(noteId: String): Unit = previewNoteDao.deleteNote(noteId)

    override suspend fun insertNote(previewNoteDTO: PreviewNoteDTO?) {
        val entity = previewNoteDTOMapper.map(previewNoteDTO)
        previewNoteDao.insertNote(entity)
    }

    override suspend fun clearNotes(): Unit = previewNoteDao.clearNotes()

}