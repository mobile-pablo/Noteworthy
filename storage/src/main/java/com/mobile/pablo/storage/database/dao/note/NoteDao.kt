package com.mobile.pablo.storage.database.dao.note

import androidx.room.*
import com.mobile.pablo.storage.database.entity.NoteEntity
import com.mobile.pablo.storage.database.entity.NoteWithDescriptionEntity
import com.mobile.pablo.storage.database.entity.NoteLineEntity
import java.util.Date

@Dao
internal abstract class NoteDao {

    @Query("SELECT * FROM notes")
    abstract suspend fun getNotes(): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE id = :id")
    abstract suspend fun getNote(id: Int?): NoteEntity

    @Query("SELECT * FROM note_line WHERE parentNoteId = :id")
    abstract suspend fun getNoteLines(id: Int?): List<NoteLineEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertNote(noteEntity: NoteEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertEmptyWithId(noteEntity: NoteEntity?) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertNoteLine(noteEntity: NoteLineEntity?)

    @Query("DELETE FROM notes where id = :noteId")
    abstract suspend fun deleteNote(noteId: Int)

    @Query("DELETE FROM note_line WHERE parentNoteId = :parentNoteId")
    abstract suspend fun deleteNoteLine(parentNoteId: Int)

    @Query("DELETE FROM notes")
    abstract suspend fun clearNotes()

    @Query("DELETE FROM note_line")
    abstract suspend fun clearNoteLines()

    @Transaction
    open suspend fun insertNoteWithDescription(
        noteWithDescriptionEntity: NoteWithDescriptionEntity
    ) {
        insertNote(noteWithDescriptionEntity.noteEntity)
        noteWithDescriptionEntity.noteLineEntityList.forEach {
            insertNoteLine(it)
        }
    }

    @Transaction
    open  suspend fun insertEmptyNote() : Long =  insertEmptyWithId(NoteEntity(title = "", date = Date()))

    @Transaction
    open suspend fun getNotesWithDescriptions(): List<NoteWithDescriptionEntity> = getNotes().map {
            getNotesWithDescriptions(it.id)
    }

    @Transaction
    open suspend fun getNotesWithDescriptions(id: Int?): NoteWithDescriptionEntity =
        NoteWithDescriptionEntity(
            noteEntity = getNote(id),
            noteLineEntityList = getNoteLines(id)
        )

    @Transaction
    open suspend fun clearNotesWithDescriptions() {
        clearNotes()
        clearNoteLines()
    }

    @Transaction
    open suspend fun deleteNoteWithDescription(id: Int) {
        deleteNote(id)
        deleteNoteLine(id)
    }
}