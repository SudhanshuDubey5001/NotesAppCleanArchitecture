package com.sudhanshu.notesappcleanarch.feature_note.presentation.notes

import com.sudhanshu.notesappcleanarch.feature_note.domain.model.Note
import com.sudhanshu.notesappcleanarch.feature_note.domain.utils.NoteOrder
import com.sudhanshu.notesappcleanarch.feature_note.domain.utils.OrderType

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreDeletedNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}
