package com.mobile.pablo.storage.source.note

import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.core.model.note.NoteDTO
import kotlinx.coroutines.flow.Flow

interface NoteDataStorage {

    fun getNote(noteId: Int): Flow<NoteDTO?>

     fun getNotes(): Flow<List<NoteDTO?>>

    suspend fun deleteNote(noteId: Int)

    suspend fun insertNote(dto: NoteDTO?)

    suspend fun insertEmptyNote() : Long

    suspend fun insertEmptyNoteLine(parentId : Int) : Long

    suspend fun clearNotes()
}