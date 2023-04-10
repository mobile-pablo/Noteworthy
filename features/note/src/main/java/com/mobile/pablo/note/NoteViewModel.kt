package com.mobile.pablo.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.utils.SingleLiveEvent
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.usecase.note.NoteUseCase
import com.mobile.pablo.common.StringRes.INTERNET_ISSUE
import com.mobile.pablo.core.utils.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getNotesUseCase: NoteUseCase.GetNotes,
    private val deleteNoteUseCase: NoteUseCase.DeleteNote,
    private val insertEmptyNoteUseCase: NoteUseCase.InsertEmptyNote
) : ViewModel(), NoteInterface {

    private var getNotesJob: Job? = null
    private var deleteNoteJob: Job? = null

    private val _notes: MutableStateFlow<List<Note?>?> = MutableStateFlow(null)
    val notes: StateFlow<List<Note?>?> = _notes.asStateFlow()

    private val _emptyNoteId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val emptyNoteId: StateFlow<Long?> = _emptyNoteId.asStateFlow()

    private val _viewState = SingleLiveEvent<com.mobile.pablo.editnote.ViewState>()
    val viewState: LiveData<com.mobile.pablo.editnote.ViewState> = _viewState

    init {
        downloadNotes()
    }

    override fun downloadNotes() {
        getNotesJob?.cancel()
        getNotesJob = launch {
            val noteResult = getNotesUseCase()
            _viewState.value = noteResult.run {
                if (isSuccessful && data != null) {
                    _notes.emit(data)
                    com.mobile.pablo.editnote.ViewState.DownloadSuccessful
                } else com.mobile.pablo.editnote.ViewState.Error(INTERNET_ISSUE)
            }
        }
    }

    override fun deleteNote(noteId: Int) {
        deleteNoteJob?.cancel()
        deleteNoteJob = launch {
            deleteNoteUseCase(noteId)
        }
    }

    override fun insertEmptyNote() {
        deleteNoteJob?.cancel()
        deleteNoteJob = launch {
            val noteResult = insertEmptyNoteUseCase()
            _viewState.value = noteResult.run {
                if (isSuccessful && data != null) {
                    _emptyNoteId.emit(data)
                    com.mobile.pablo.editnote.ViewState.InsertSuccessful
                } else com.mobile.pablo.editnote.ViewState.Error(INTERNET_ISSUE)
            }
        }
    }
}

sealed class ViewState {
    object DownloadSuccessful : com.mobile.pablo.editnote.ViewState()
    object InsertSuccessful : com.mobile.pablo.editnote.ViewState()
    class Error(val message: String) : com.mobile.pablo.editnote.ViewState()
}