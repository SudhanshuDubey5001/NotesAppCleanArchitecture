package com.sudhanshu.notesappcleanarch.feature_note.domain.use_case

import com.sudhanshu.notesappcleanarch.feature_note.domain.model.InvalidNoteException
import com.sudhanshu.notesappcleanarch.feature_note.domain.model.Note
import com.sudhanshu.notesappcleanarch.feature_note.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddNote(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (!checkIfNoteIsBlank(note)) throw InvalidNoteException("Title or Content cannot be empty")
        repository.insertNote(note)
    }

    private fun checkIfNoteIsBlank(note: Note): Boolean {
        return note.title.isBlank() || note.content.isBlank()
    }
}