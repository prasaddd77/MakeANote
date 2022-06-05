package com.prasad.makeanote.repository

import androidx.lifecycle.LiveData
import com.prasad.makeanote.database.NotesDatabase
import com.prasad.makeanote.model.Notes
import com.prasad.makeanote.notedao.NotesDao

class NotesRepository(val dao : NotesDao) {
    fun getAllNotes() : LiveData<List<Notes>> {
        return dao.getAllNotes()
    }

    fun getHighNotes() : LiveData<List<Notes>>{
        return dao.getHighNotes()
    }

    fun getMediumNotes() : LiveData<List<Notes>>{
        return dao.getMediumNotes()
    }

    fun getLowNotes() : LiveData<List<Notes>>{
        return dao.getLowNotes()
    }

    fun insertNotes(notes:Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id:Int){
        dao.deleteNotes(id)

    }

    fun updateNotes(notes : Notes){
        dao.updateNotes(notes)
    }
}