package com.sudhanshu.notesappcleanarch.feature_note.presentation.notes

import com.sudhanshu.notesappcleanarch.feature_note.domain.model.Note
import com.sudhanshu.notesappcleanarch.feature_note.domain.utils.NoteOrder
import com.sudhanshu.notesappcleanarch.feature_note.domain.utils.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSelectionVisible: Boolean = false
)
