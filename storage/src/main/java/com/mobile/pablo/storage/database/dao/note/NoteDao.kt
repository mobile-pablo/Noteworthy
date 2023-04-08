package com.mobile.pablo.storage.database.dao.note

import androidx.room.*
import com.mobile.pablo.storage.database.entity.NoteEntity
import com.mobile.pablo.storage.database.entity.NoteWithDescriptionEntity
import com.mobile.pablo.storage.database.entity.NoteLineEntity
import java.util.Date

@Dao
internal abstract class NoteDao {

    @Query("SELECT * FROM notes")
    abstract suspend fun getFullNotes(): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE id = :id")
    abstract suspend fun getFullNote(id: Int?): NoteEntity

    @Query("SELECT * FROM note_line WHERE fullNoteId = :id")
    abstract suspend fun getNoteLines(id: Int?): List<NoteLineEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertFullNote(noteEntity: NoteEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertEmptyWithId(noteEntity: NoteEntity?) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertNoteLine(fullNoteEntity: NoteLineEntity?)

    @Query("DELETE FROM notes where id = :noteId")
    abstract suspend fun deleteFullNote(noteId: Int)

    @Query("DELETE FROM note_line WHERE fullNoteId = :fullNoteId")
    abstract suspend fun deleteNoteLine(fullNoteId: Int)

    @Query("DELETE FROM notes")
    abstract suspend fun clearFullNotes()

    @Query("DELETE FROM note_line")
    abstract suspend fun clearNoteLines()

    @Transaction
    open suspend fun insertNoteWithDescription(
        noteWithDescriptionEntity: NoteWithDescriptionEntity
    ) {
        insertFullNote(noteWithDescriptionEntity.noteEntity)
        noteWithDescriptionEntity.noteLineEntityList.forEach {
            insertNoteLine(it)
        }
    }

    @Transaction
    open  suspend fun insertEmptyNote() : Long =  insertEmptyWithId(NoteEntity(title = "", date = Date()))

    @Transaction
    open suspend fun getNotesWithDescriptions(): List<NoteWithDescriptionEntity> {
        val fullNotes = getFullNotes()

        return fullNotes.map {
            getNotesWithDescriptions(it.id)
        }
    }

    @Transaction
    open suspend fun getNotesWithDescriptions(id: Int?): NoteWithDescriptionEntity =
        NoteWithDescriptionEntity(
            noteEntity = getFullNote(id),
            noteLineEntityList = getNoteLines(id)
        )

    @Transaction
    open suspend fun clearNotesWithDescriptions() {
        clearFullNotes()
        clearNoteLines()
    }

    @Transaction
    open suspend fun deleteNoteWithDescription(id: Int) {
        deleteFullNote(id)
        deleteNoteLine(id)
    }
}