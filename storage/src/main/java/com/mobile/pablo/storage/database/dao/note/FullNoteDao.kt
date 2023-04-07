package com.mobile.pablo.storage.database.dao.note

import androidx.room.*
import com.mobile.pablo.storage.database.entity.FullNoteEntity
import com.mobile.pablo.storage.database.entity.FullNoteWithDescriptionEntity
import com.mobile.pablo.storage.database.entity.NoteLineEntity

@Dao
internal abstract class FullNoteDao {

    @Query("SELECT * FROM notes")
    abstract suspend fun getFullNotes(): List<FullNoteEntity>

    @Query("SELECT * FROM notes WHERE id = :id")
    abstract suspend fun getFullNote(id: String): FullNoteEntity

    @Query("SELECT * FROM note_line WHERE fullNoteId = :id")
    abstract suspend fun getNoteLines(id: String): List<NoteLineEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertFullNote(fullNoteEntity: FullNoteEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertNoteLine(fullNoteEntity: NoteLineEntity?)

    @Query("DELETE FROM notes where id = :noteId")
    abstract suspend fun deleteNote(noteId: String)

    @Query("DELETE FROM notes")
    abstract suspend fun clearNotes()

    @Transaction
    open suspend fun insertNoteWithDescription(
        fullNoteWithDescriptionEntity: FullNoteWithDescriptionEntity
    ) {
        insertFullNote(fullNoteWithDescriptionEntity.fullNoteEntity)
        fullNoteWithDescriptionEntity.noteLineEntityList.forEach {
            insertNoteLine(it)
        }
    }

    @Transaction
    open suspend fun getNotesWithDescriptions(): List<FullNoteWithDescriptionEntity> {
        val fullNotes = getFullNotes()

        return fullNotes.map {
            getNotesWithDescriptions(it.id)
        }
    }

    @Transaction
    open suspend fun getNotesWithDescriptions(id: String): FullNoteWithDescriptionEntity =
        FullNoteWithDescriptionEntity(
            fullNoteEntity = getFullNote(id),
            noteLineEntityList = getNoteLines(id)
        )
}