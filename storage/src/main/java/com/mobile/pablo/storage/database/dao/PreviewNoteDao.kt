package com.mobile.pablo.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.pablo.storage.database.entity.PreviewNoteEntity

@Dao
internal abstract class PreviewNoteDao {


    @Query("SELECT * FROM preview_notes")
    abstract suspend fun getNotes() : List<PreviewNoteEntity?>

    @Query("DELETE FROM notes where id = :noteId")
    abstract suspend fun deleteNote(noteId : String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertNote(previewNoteEntity: PreviewNoteEntity)

    @Query("DELETE FROM notes")
    abstract suspend fun clearNotes()
}