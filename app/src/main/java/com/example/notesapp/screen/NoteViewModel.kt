package com.example.notesapp.screen

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.notesapp.Data.NotesDataSourc
import com.example.notesapp.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteViewModel:ViewModel() {

    private var noteList = mutableStateListOf<Note>()

    private val _title= MutableStateFlow("")
    val title = _title.asStateFlow()

    fun setName(title: String) {
        _title.value = title
    }
    private val _description= MutableStateFlow("")
    val description = _description.asStateFlow()

    fun setdescription(description: String) {
        _description.value = description
    }

    init{
        noteList.addAll(NotesDataSourc().loadNotes())
    }


    fun addNote(note:Note)
    {
        noteList.add(note)
    }
    fun removeNote(note:Note)
    {
        noteList.remove(note)
    }
    fun getallNotes():List<Note>{
        return noteList
    }

}