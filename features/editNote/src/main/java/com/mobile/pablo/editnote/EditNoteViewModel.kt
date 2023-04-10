package com.mobile.pablo.editnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.utils.SingleLiveEvent
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.usecase.note.NoteUseCase
import com.mobile.pablo.core.utils.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private var getNoteUseCase: NoteUseCase.GetNote,
    private var insertNoteUseCase: NoteUseCase.InsertNote,
    private var createEmptyNoteLineUseCase: NoteUseCase.InsertEmptyNoteLine,
    private val deleteNoteUseCase: NoteUseCase.DeleteNote
) : ViewModel(), EditNoteInterface {

    private var noteJob: Job? = null

    private val _note: MutableStateFlow<Note?> = MutableStateFlow(null)
    val note: StateFlow<Note?> = _note.asStateFlow()

    private val _viewState = SingleLiveEvent<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    private val _emptyNoteLineId: MutableStateFlow<Long> = MutableStateFlow(0L)
    val emptyNoteLineId: StateFlow<Long> = _emptyNoteLineId.asStateFlow()

    override fun downloadNote(noteId: Int) {
        noteJob?.cancel()
        noteJob = launch {
            val notesResult = getNoteUseCase(noteId)
            notesResult.run {
                if (isSuccessful && data != null)
                    _note.emit(data)
            }
        }
    }

    override fun createEmptyNoteLine(parentNoteId: Int) {
        noteJob?.cancel()
        noteJob = launch {
            val notesResult = createEmptyNoteLineUseCase(parentNoteId)
            _emptyNoteLineId.emit(notesResult)
        }
    }

    override fun saveNote(note: Note) {
        noteJob?.cancel()
        noteJob = launch {
            insertNoteUseCase(note)
        }
    }

    override fun pinNote(noteId: Int) {
        TODO("Not yet implemented")
    }

    override fun shareNote() {
        TODO("Not yet implemented")
    }
}

sealed class ViewState {
    object DownloadSuccessful : ViewState()
    class Error(val message: String) : ViewState()
}