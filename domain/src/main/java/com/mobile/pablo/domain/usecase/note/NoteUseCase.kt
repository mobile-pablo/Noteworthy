package com.mobile.pablo.domain.usecase.note

import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.mapper.note.NoteMapper
import com.mobile.pablo.storage.source.note.NoteDataStorage
import javax.inject.Inject

sealed class NoteUseCase {

    class GetNotes @Inject constructor(
        private val noteMapper: NoteMapper,
        private val noteDataStorage: NoteDataStorage
    ) {

        suspend operator fun invoke(): DataTransfer<List<Note?>> {
            val response = noteDataStorage.getNotes()
            return DataTransfer(response.map(noteMapper::map))
        }

    }

    class GetNote @Inject constructor(
        private val noteMapper: NoteMapper,
        private val noteDataStorage: NoteDataStorage
    ) {

        suspend operator fun invoke(noteId: Int): DataTransfer<Note?> {
            val response = noteDataStorage.getNote(noteId)
            return DataTransfer(noteMapper.map(response))
        }

    }

    class InsertNote @Inject constructor(
        private val noteMapper: NoteMapper,
        private val noteDataStorage: NoteDataStorage,
    ) {

        suspend operator fun invoke(note: Note) {
            val response = noteMapper.map(note)
            noteDataStorage.insertNote(response)
        }
    }

    class InsertEmptyNote @Inject constructor(
        private val noteDataStorage: NoteDataStorage,
    ) {
        suspend operator fun invoke(): DataTransfer<Long> = DataTransfer(noteDataStorage.insertEmptyNote())
    }

    class InsertEmptyNoteLine @Inject constructor(
        private val noteDataStorage: NoteDataStorage,
    ) {
        suspend operator fun invoke(parentNoteId : Int): Long =noteDataStorage
            .insertEmptyNoteLine(parentNoteId)
    }

    class DeleteNote @Inject constructor(
        private val noteDataStorage: NoteDataStorage,
    ) {

        suspend operator fun invoke(noteId: Int) =
            noteDataStorage.deleteNote(noteId)
    }

    class ClearNotes @Inject constructor(
        private val noteDataStorage: NoteDataStorage,
    ) {

        suspend operator fun invoke() =
            noteDataStorage.clearNotes()
    }
}