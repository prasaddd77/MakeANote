package com.prasad.makeanote.notedao

import androidx.core.app.NotificationBuilderWithBuilderAccessor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.prasad.makeanote.model.Notes

@Dao
interface NotesDao {

    @Query("Select * From Notes Order By id Asc")
    fun getAllNotes() : LiveData<List<Notes>>

    @Query("Select * From Notes Where priority=3")
    fun getHighNotes():LiveData<List<Notes>>

    @Query("Select * From Notes Where priority=2")
    fun getMediumNotes():LiveData<List<Notes>>

    @Query("Select * From Notes Where priority=1")
    fun getLowNotes():LiveData<List<Notes>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id:Int)

    @Update
    fun updateNotes(notes: Notes)



}