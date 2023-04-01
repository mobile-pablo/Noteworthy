package com.mobile.pablo.storage.database.dao.note

import androidx.room.*
import com.mobile.pablo.storage.database.entity.FullNoteEntity

@Dao
internal abstract class FullNoteDao {

    @Query("SELECT * FROM notes")
    abstract suspend fun getNotes(): List<FullNoteEntity?>

    @Query("DELETE FROM notes where id = :noteId")
    abstract suspend fun deleteNote(noteId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertNote(fullNoteEntity: FullNoteEntity?)

    @Query("DELETE FROM notes")
    abstract suspend fun clearNotes()
}