package com.example.notesapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesapp.R
import com.example.notesapp.components.NoteButton
import com.example.notesapp.components.NoteInputTex
import com.example.notesapp.model.Note
import com.example.notesapp.screen.NoteViewModel



@Composable
fun NoteScreen(notes:List<Note>,
               noteViewModel:NoteViewModel= viewModel(),
                onAddNote: (Note)->Unit,
                onRemoveNote:(Note)->Unit
               )  {


    val context= LocalContext.current
    val title=noteViewModel.title.collectAsState()
    val description=noteViewModel.description.collectAsState()

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = null
                )
            }, backgroundColor = Color(0xFFDADFE3),
        )
        //Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputTex(modifier=Modifier.padding(top = 9.dp , bottom=8.dp),textt = title.value, label = "Title", onTextChange = {if(it.all{
                it.isLetter() ||it.isWhitespace()
                })noteViewModel.setName(it)})

            NoteInputTex(modifier=Modifier.padding(top = 9.dp , bottom=8.dp),textt = description.value, label = "Add a note", onTextChange = {
                if(it.all{
                        it.isLetter() ||it.isWhitespace()
                    })noteViewModel.setdescription(it)})



            NoteButton(text = "Save", onClick = {
                if(title.value.isNotEmpty() && description.value.isNotEmpty())
                {
                    //save or add to the list
                    onAddNote(Note(title=title.value, description = description.value))
                    noteViewModel.setName("")
                    noteViewModel.setdescription("")
                    Toast.makeText(context,"Saved Sucessfully",Toast.LENGTH_SHORT).show()

                }

            })
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn{
           items(notes){ note ->
              NoteRow(note = note, onNoteClicked = {onRemoveNote(it)})
           }
        }
    }


}


@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note:Note,
    onNoteClicked: (Note) -> Unit )
{

   Surface(
       modifier
           .padding(4.dp)
           .fillMaxWidth()
           .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp)),color=Color(
           0xFFF1B1F8
       ),elevation=6.dp)
    {
      Column(
          modifier
              .clickable { onNoteClicked(note) }
              .padding(14.dp, vertical = 6.dp), horizontalAlignment = Alignment.Start
      )
      {
            Text(text=note.title,style=MaterialTheme.typography.subtitle2)
            Text(text=note.description,style=MaterialTheme.typography.subtitle1)

      }
   }


}

