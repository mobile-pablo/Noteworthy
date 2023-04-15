package com.mobile.pablo.domain.usecase.note

import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.mapper.note.NoteMapper
import com.mobile.pablo.storage.source.note.NoteDataStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

sealed class NoteUseCase {

    class GetNotes @Inject constructor(
        private val noteMapper: NoteMapper,
        private val noteDataStorage: NoteDataStorage
    ) {

        operator fun invoke(): Flow<List<Note?>> {
            val response = noteDataStorage.getNotes()
            return response.map {
                it.map(noteMapper::map)
            }
        }
    }

    class GetNote @Inject constructor(
        private val noteMapper: NoteMapper,
        private val noteDataStorage: NoteDataStorage
    ) {

        operator fun invoke(noteId: Int): Flow<Note?> {
            val response = noteDataStorage.getNote(noteId)
            return response.map(noteMapper::map)
        }

    }

    class InsertNote @Inject constructor(
        private val noteMapper: NoteMapper,
        private val noteDataStorage: NoteDataStorage,
    ) {

        suspend operator fun invoke(note: Note) : DataTransfer<Unit>{
            val response = noteMapper.map(note)
            val storageResult = noteDataStorage.insertNote(response)
            return DataTransfer(storageResult)
        }
    }

    class InsertEmptyNote @Inject constructor(
        private val noteDataStorage: NoteDataStorage,
    ) {

        suspend operator fun invoke(): DataTransfer<Long> =
            DataTransfer(noteDataStorage.insertEmptyNote())
    }

    class InsertEmptyNoteLine @Inject constructor(
        private val noteDataStorage: NoteDataStorage,
    ) {

        suspend operator fun invoke(parentNoteId: Int): Long =
            noteDataStorage.insertEmptyNoteLine(parentNoteId)
    }

    class DeleteNote @Inject constructor(
        private val noteDataStorage: NoteDataStorage,
    ) {

        suspend operator fun invoke(noteId: Int): DataTransfer<Unit> =
            DataTransfer(noteDataStorage.deleteNote(noteId))
    }

    class ClearNotes @Inject constructor(
        private val noteDataStorage: NoteDataStorage,
    ) {

        suspend operator fun invoke() =
            noteDataStorage.clearNotes()
    }
}