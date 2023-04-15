package com.mobile.pablo.editnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.utils.SingleLiveEvent
import com.mobile.pablo.core.utils.launch
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.usecase.note.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private var getNoteUseCase: NoteUseCase.GetNote,
    private var insertNoteUseCase: NoteUseCase.InsertNote,
    private val deleteNoteUseCase: NoteUseCase.DeleteNote
) : ViewModel(), EditNoteInterface {

    private var noteJob: Job? = null

    private val _viewState = SingleLiveEvent<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

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
