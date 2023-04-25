package com.mobile.pablo.editnote.mock

import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.utils.launchAsync
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.data.note.NoteLine
import com.mobile.pablo.editnote.EditNoteInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@HiltViewModel
class FakeEditNoteViewModel @Inject constructor(
) : ViewModel(), EditNoteInterface {

    private var saveJob: Job? = null

    private val _viewState = Channel<ViewState>()
    val viewState: Flow<ViewState> = _viewState.receiveAsFlow()

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
    object SaveSuccessful : ViewState()
    class Message(val message: String) : ViewState()
    object Default : ViewState()
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
        ), NoteLine(
            id = 2,
            parentNoteId = 5,
            isCheckbox = false,
            noteText = "Pizza with pineapple completed"
        )
    )
)
