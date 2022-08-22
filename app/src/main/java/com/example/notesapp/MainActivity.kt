package com.example.notesapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesapp.screen.NoteScreen
import com.example.notesapp.screen.NoteViewModel
import com.example.notesapp.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                     color = MaterialTheme.colors.background
                ) {
                    val noteViewModel:NoteViewModel  by viewModels ()

                   NotesApp(noteViewModel=noteViewModel)


                }
            }
        }
    }
}
@Composable
fun NotesApp(noteViewModel: NoteViewModel= viewModel())
{
    val notesList=noteViewModel.getallNotes()
    NoteScreen(notes= notesList,noteViewModel=noteViewModel, onAddNote={
        noteViewModel.addNote(it)
    },onRemoveNote={
        noteViewModel.removeNote(it)})
}

