package com.example.notesapp.Data

import com.example.notesapp.model.Note

class NotesDataSourc{
    fun loadNotes() : List<Note>{

        return listOf(
            Note(title = "Feeling Brave", description = "You can't lost to yourself"),
            Note(title = "Feeling Weak", description = "Being Weaker shouldn't be your choice"),
            Note(title = "Feeling Power", description = "Power is the key that everyone bows to You"),
            Note(title = "Feeling Absolutism", description = "Denying the existence of everyone and profounding the presense of yours"),
        )
    }
}