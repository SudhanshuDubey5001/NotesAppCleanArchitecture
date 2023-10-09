package com.sudhanshu.notesappcleanarch.feature_note.domain.use_case

import androidx.compose.ui.text.toLowerCase
import com.sudhanshu.notesappcleanarch.feature_note.domain.model.Note
import com.sudhanshu.notesappcleanarch.feature_note.domain.repository.NoteRepository
import com.sudhanshu.notesappcleanarch.feature_note.domain.utils.NoteOrder
import com.sudhanshu.notesappcleanarch.feature_note.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getAllNotes().map { notes ->
            when (noteOrder.orderType) {
                OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                        is NoteOrder.Date -> notes.sortedBy { it.timeStamp }
                    }
                }
                OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timeStamp }
                    }
                }
            }
        }
    }
}