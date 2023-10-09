package com.sudhanshu.notesappcleanarch.feature_note.domain.use_case

import com.sudhanshu.notesappcleanarch.feature_note.domain.model.Note
import com.sudhanshu.notesappcleanarch.feature_note.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.getNote(id)
    }
}