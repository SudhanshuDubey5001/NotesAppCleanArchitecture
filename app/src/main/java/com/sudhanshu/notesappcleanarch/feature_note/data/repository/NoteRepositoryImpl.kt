package com.sudhanshu.notesappcleanarch.feature_note.data.repository

import com.sudhanshu.notesappcleanarch.feature_note.data.data_source.NotesDao
import com.sudhanshu.notesappcleanarch.feature_note.domain.model.Note
import com.sudhanshu.notesappcleanarch.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NotesDao
): NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
    }

    override suspend fun getNote(id: Int): Note? {
        return dao.getNote(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}