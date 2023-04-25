package com.mobile.pablo.addnote.mock

import androidx.lifecycle.ViewModel
import com.mobile.pablo.addnote.AddNoteInterface
import com.mobile.pablo.core.utils.launchAsync
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.data.note.NoteLine
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@HiltViewModel
class FakeAddNoteViewModel @Inject constructor() : ViewModel(), AddNoteInterface {

    private var saveJob: Job? = null
    private var emptyJob: Job? = null

    var note: Flow<Note?> = emptyFlow()

    private val _viewState: Channel<ViewState> = Channel()
    val viewState: Flow<ViewState> = _viewState.receiveAsFlow()

    private val _emptyNoteLineId: MutableStateFlow<Long> = MutableStateFlow(0L)
    val emptyNoteLineId: StateFlow<Long> = _emptyNoteLineId.asStateFlow()

    override fun downloadNote(noteId: Int) {
        note = flowOf(LOCAL_NOTE)
    }

    override fun createEmptyNoteLine(parentNoteId: Int) {
        emptyJob?.cancel()
        emptyJob = launchAsync {
            val noteLines = LOCAL_NOTE.description
            val notesResult = NoteLine(
                parentNoteId = parentNoteId,
                id = noteLines.size + 1,
                isCheckbox = true
            )

            LOCAL_NOTE = LOCAL_NOTE.copy(description = noteLines.plus(notesResult))
            _emptyNoteLineId.emit(noteLines.size + 1.toLong())
        }
    }

    override fun saveNote(note: Note) {
        saveJob?.cancel()
        saveJob = launchAsync {
            LOCAL_NOTE = note
            val viewState = ViewState.SaveSuccessful
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

private var LOCAL_NOTE = Note(
    id = 5,
    title = "Pizza with Pineapple : Tutorial",
    date = Date(),
    description = listOf(
        NoteLine(
            id = 0,
            parentNoteId = 5,
            isCheckbox = true,
            noteText = "Find a pizza"
        ),
        NoteLine(
            id = 1,
            parentNoteId = 5,
            isCheckbox = false,
            noteText = "Add pineapple"
        ),
        NoteLine(
            id = 2,
            parentNoteId = 5,
            isCheckbox = false,
            noteText = "Pizza with pineapple completed"
        )
    )
)
