package com.mobile.pablo.storage.database.dao.note

import androidx.room.*
import com.mobile.pablo.storage.database.entity.FullNoteEntity
import com.mobile.pablo.storage.database.entity.FullNoteWithDescriptionEntity
import com.mobile.pablo.storage.database.entity.FullNoteWithDescriptionWrapper
import com.mobile.pablo.storage.database.entity.NoteLineEntity

@Dao
internal abstract class FullNoteDao {

    @Query("SELECT * FROM FullNoteWithDescriptionWrapper")
    abstract suspend fun getNotes(): List<FullNoteWithDescriptionWrapper?>

    @Query("DELETE FROM notes where id = :noteId")
    abstract suspend fun deleteNote(noteId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertFullNote(fullNoteEntity: FullNoteEntity?) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertNoteLine(fullNoteEntity: NoteLineEntity?)  : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertNote(fullNoteEntity: FullNoteWithDescriptionEntity?)

    @Query("DELETE FROM notes")
    abstract suspend fun clearNotes()
}