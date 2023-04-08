package com.mobile.pablo.storage.database.dao.note

import androidx.room.*
import com.mobile.pablo.storage.database.entity.FullNoteEntity
import com.mobile.pablo.storage.database.entity.FullNoteWithDescriptionEntity
import com.mobile.pablo.storage.database.entity.NoteLineEntity
import java.util.Date

@Dao
internal abstract class FullNoteDao {

    @Query("SELECT * FROM notes")
    abstract suspend fun getFullNotes(): List<FullNoteEntity>

    @Query("SELECT * FROM notes WHERE id = :id")
    abstract suspend fun getFullNote(id: Int?): FullNoteEntity

    @Query("SELECT * FROM note_line WHERE fullNoteId = :id")
    abstract suspend fun getNoteLines(id: Int?): List<NoteLineEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertFullNote(fullNoteEntity: FullNoteEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertEmptyWithId(fullNoteEntity: FullNoteEntity?) : Long

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
        fullNoteWithDescriptionEntity: FullNoteWithDescriptionEntity
    ) {
        insertFullNote(fullNoteWithDescriptionEntity.fullNoteEntity)
        fullNoteWithDescriptionEntity.noteLineEntityList.forEach {
            insertNoteLine(it)
        }
    }

    @Transaction
    open  suspend fun insertEmptyNote() : Long =  insertEmptyWithId(FullNoteEntity(title = "", date = Date()))

    @Transaction
    open suspend fun getNotesWithDescriptions(): List<FullNoteWithDescriptionEntity> {
        val fullNotes = getFullNotes()

        return fullNotes.map {
            getNotesWithDescriptions(it.id)
        }
    }

    @Transaction
    open suspend fun getNotesWithDescriptions(id: Int?): FullNoteWithDescriptionEntity =
        FullNoteWithDescriptionEntity(
            fullNoteEntity = getFullNote(id),
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