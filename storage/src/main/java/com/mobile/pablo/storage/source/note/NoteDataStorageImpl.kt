package com.mobile.pablo.storage.source.note

import com.mobile.pablo.core.model.note.NoteDTO
import com.mobile.pablo.storage.database.dao.note.NoteDao
import com.mobile.pablo.storage.database.entity.NoteWithDescriptionEntity
import com.mobile.pablo.storage.mapper.note.NoteDTOMapper
import com.mobile.pablo.storage.mapper.note.NoteLineDTOMapper
import javax.inject.Inject

internal class NoteDataStorageImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val noteDTOMapper: NoteDTOMapper,
    private val noteLineDTOMapper: NoteLineDTOMapper
) : NoteDataStorage {

    override suspend fun getNotes(): List<NoteDTO?> {
        val dao = noteDao.getNotesWithDescriptions()

        return dao.map {
            noteDTOMapper.map(
                it.noteEntity,
                it.noteLineEntityList
            )
        }
    }

    override suspend fun getNote(noteId: Int): NoteDTO? {
        val dao = noteDao.getNotesWithDescriptions(noteId)
        return noteDTOMapper.map(
            dao.noteEntity,
            dao.noteLineEntityList
        )
    }

    override suspend fun deleteNote(noteId: Int): Unit = noteDao.deleteNoteWithDescription(noteId)

    override suspend fun insertNote(dto: NoteDTO?) {
        val entity = noteDTOMapper.map(dto)
        val description = dto?.description?.map(noteLineDTOMapper::map)

        noteDao.insertNoteWithDescription(
            NoteWithDescriptionEntity(
                entity,
                description
            )
        )
    }

    override suspend fun insertEmptyNote(): Long = noteDao.insertEmptyNote()

    override suspend fun clearNotes(): Unit = noteDao.clearNotesWithDescriptions()
}