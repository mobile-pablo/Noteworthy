package com.mobile.pablo.addnote

import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.utils.launch
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.usecase.note.NoteUseCase
import com.mobile.pablo.uicomponents.common.util.StringRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private var getNoteUseCase: NoteUseCase.GetNote,
    private var insertNoteUseCase: NoteUseCase.InsertNote,
    private var createEmptyNoteLineUseCase: NoteUseCase.InsertEmptyNoteLine,
) : ViewModel(), AddNoteInterface {

    private var noteJob: Job? = null

    var note: Flow<Note?> = emptyFlow()

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Default)
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    private val _emptyNoteLineId: MutableStateFlow<Long> = MutableStateFlow(0L)
    val emptyNoteLineId: StateFlow<Long> = _emptyNoteLineId.asStateFlow()

    override fun downloadNote(noteId: Int) {
        note = getNoteUseCase(noteId)
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
            val noteResult = insertNoteUseCase(note)
            _viewState.value = noteResult.run {
                if (isSuccessful && data != null) {
                    ViewState.SaveSuccessful
                } else ViewState.Message(StringRes.INTERNET_ISSUE)
            }
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
    object Default : ViewState()
    object SaveSuccessful : ViewState()
    class Message(val message: String) : ViewState()
}