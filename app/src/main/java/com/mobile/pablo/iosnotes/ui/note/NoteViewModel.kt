package com.mobile.pablo.iosnotes.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.utils.SingleLiveEvent
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.usecase.note.NoteUseCase
import com.mobile.pablo.iosnotes.util.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class NoteViewModel @Inject constructor(
    private var getNote: NoteUseCase.GetNote,
    private var insertNote: NoteUseCase.InsertNote,
    private val deleteNote: NoteUseCase.DeleteNote
) : ViewModel(), NoteInterface {

    private var noteJob: Job? = null

    private val _note: MutableStateFlow<Note?> = MutableStateFlow(null)
    val note: StateFlow<Note?> = _note.asStateFlow()

    private val _viewState = SingleLiveEvent<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    override fun downloadNote(noteId: Int) {
        noteJob?.cancel()
        noteJob = launch {
            val notesResult = getNote(noteId)
            notesResult.run {
                if (isSuccessful && data != null)
                    _note.emit(data)
            }
        }
    }

    override fun saveNote(note: Note) {
        noteJob?.cancel()
        noteJob = launch {
            insertNote(note)
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