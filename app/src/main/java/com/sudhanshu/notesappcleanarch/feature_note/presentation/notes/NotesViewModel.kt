package com.sudhanshu.notesappcleanarch.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudhanshu.notesappcleanarch.feature_note.domain.model.Note
import com.sudhanshu.notesappcleanarch.feature_note.domain.use_case.NoteUseCases
import com.sudhanshu.notesappcleanarch.feature_note.domain.utils.NoteOrder
import com.sudhanshu.notesappcleanarch.feature_note.domain.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getAllNotesJob: Job? = null

    init {
        //get all the notes and arrange it in default order
        getNotesByGivenOrder(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvents(event: NotesEvent) {
        when (event) {
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }

            is NotesEvent.Order -> {
                //first check if the user taps the same order again -> then do nothing
                //we compare the classes because without classes, we are just comparing the references
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType::class == event.noteOrder.orderType::class
                )
                    return
                getNotesByGivenOrder(event.noteOrder)
            }

            NotesEvent.RestoreDeletedNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }

            NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSelectionVisible = !state.value.isOrderSelectionVisible
                )
            }
        }
    }

    private fun getNotesByGivenOrder(noteOrder: NoteOrder) {
        //we also want to cancel the current coroutine observing the database and launch a new one
        getAllNotesJob?.cancel()
        getAllNotesJob = noteUseCases.getAllNotes(noteOrder)
            //whenever there is a change in order, we want to map the notes state with the new values
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }.launchIn(viewModelScope)
    }
}