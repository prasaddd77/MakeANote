package com.prasad.makeanote.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.prasad.makeanote.database.NotesDatabase
import com.prasad.makeanote.model.Notes
import com.prasad.makeanote.notedao.NotesDao
import com.prasad.makeanote.repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var repository : NotesRepository

    init{
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)

    }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }
    fun getAllNotes() : LiveData<List<Notes>> = repository.getAllNotes()

    fun getHightNotes(): LiveData<List<Notes>> = repository.getHighNotes()

    fun getMediumNotes() : LiveData<List<Notes>> = repository.getMediumNotes()

    fun getLowNotes() : LiveData<List<Notes>> = repository.getLowNotes()

    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes:Notes){
        repository.updateNotes(notes)
    }
}
