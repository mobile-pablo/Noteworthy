package com.mobile.pablo.storage.source.note

import com.mobile.pablo.core.model.note.NoteDTO
import com.mobile.pablo.storage.database.dao.note.NoteDao
import com.mobile.pablo.storage.database.entity.NoteWithDescriptionEntity
import com.mobile.pablo.storage.mapper.note.NoteDTOMapper
import com.mobile.pablo.storage.mapper.note.NoteLineDTOMapper
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class NoteDataStorageImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val noteDTOMapper: NoteDTOMapper,
    private val noteLineDTOMapper: NoteLineDTOMapper
) : NoteDataStorage {

    override fun getNotes(): Flow<List<NoteDTO?>> {
        val dao = noteDao.getNotesWithDescriptions()

        return dao.map {
            it.map { note ->
                noteDTOMapper.map(
                    note.noteEntity,
                    note.noteLineEntityList
                )
            }
        }
    }

    override fun getNote(noteId: Int): Flow<NoteDTO?> {
        val dao = noteDao.getNotesWithDescriptions(noteId)
        return dao.map {
            noteDTOMapper.map(
                it.noteEntity,
                it.noteLineEntityList
            )
        }
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

    override suspend fun insertEmptyNoteLine(parentId: Int): Long = noteDao.insertEmptyNoteLineWithId(parentId)

    override suspend fun clearNotes(): Unit = noteDao.clearNotesWithDescriptions()
}
