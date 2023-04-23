package com.mobile.pablo.addnote

import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.utils.launchAsync
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.usecase.note.NoteUseCase
import com.mobile.pablo.uicomponents.common.util.StringRes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private var getNoteUseCase: NoteUseCase.GetNote,
    private var insertNoteUseCase: NoteUseCase.InsertNote,
    private var createEmptyNoteLineUseCase: NoteUseCase.InsertEmptyNoteLine
) : ViewModel(), AddNoteInterface {

    private var saveJob: Job? = null
    private var emptyJob: Job? = null

    var note: Flow<Note?> = emptyFlow()

    private val _viewState: Channel<ViewState> = Channel()
    val viewState: Flow<ViewState> = _viewState.receiveAsFlow()

    private val _emptyNoteLineId: MutableStateFlow<Long> = MutableStateFlow(0L)
    val emptyNoteLineId: StateFlow<Long> = _emptyNoteLineId.asStateFlow()

    override fun downloadNote(noteId: Int) {
        note = getNoteUseCase(noteId)
    }

    override fun createEmptyNoteLine(parentNoteId: Int) {
        emptyJob?.cancel()
        emptyJob = launchAsync {
            val notesResult = createEmptyNoteLineUseCase(parentNoteId)
            _emptyNoteLineId.emit(notesResult)
        }
    }

    override fun saveNote(note: Note) {
        saveJob?.cancel()
        saveJob = launchAsync {
            val noteResult = insertNoteUseCase(note)
            val viewState = noteResult.run {
                if (isSuccessful && data != null) {
                    ViewState.SaveSuccessful
                } else ViewState.Message(StringRes.INTERNET_ISSUE)
            }

            _viewState.send(viewState)
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
