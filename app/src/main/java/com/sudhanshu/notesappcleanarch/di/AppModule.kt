package com.sudhanshu.notesappcleanarch.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sudhanshu.notesappcleanarch.feature_note.data.data_source.NotesDatabase
import com.sudhanshu.notesappcleanarch.feature_note.data.repository.NoteRepositoryImpl
import com.sudhanshu.notesappcleanarch.feature_note.domain.repository.NoteRepository
import com.sudhanshu.notesappcleanarch.feature_note.domain.use_case.AddNote
import com.sudhanshu.notesappcleanarch.feature_note.domain.use_case.DeleteNote
import com.sudhanshu.notesappcleanarch.feature_note.domain.use_case.GetAllNotes
import com.sudhanshu.notesappcleanarch.feature_note.domain.use_case.GetNote
import com.sudhanshu.notesappcleanarch.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNotesDatabase(
        @ApplicationContext app: Context
    ): NotesDatabase {
        return Room.databaseBuilder(
            app,
            NotesDatabase::class.java,
            NotesDatabase.DATABASE_NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(db: NotesDatabase): NoteRepository {
        return NoteRepositoryImpl(db.notesDao)
    }

    @Provides
    @Singleton
    fun providesNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getAllNotes = GetAllNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}