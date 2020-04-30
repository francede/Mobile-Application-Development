package com.example.notepad.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notepad.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note LIMIT 1")
    fun getNote(): LiveData<Note?>

    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

}
