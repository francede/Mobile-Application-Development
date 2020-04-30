package com.example.notepad.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.notepad.model.Note

public class NoteRepository(context: Context) {

    private var noteDao: NoteDao

    init {
        val reminderRoomDatabase = NoteRoomDatabase.getDatabase(context)
        noteDao = reminderRoomDatabase!!.noteDao()
    }

    fun getNote(): LiveData<Note?> {
        return noteDao.getNote()
    }

    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

}
