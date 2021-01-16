package com.example.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), onNoteClickListner {

    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adepter = NotesAdepter(this,this)
        recyclerView.adapter = adepter
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            //observer is a interface wich has only one fun so we can implemnt e=that directly
            list?.let {
                adepter.updateList(list)
            }
        })
    }

    override fun onClicked(note: Note) {
        viewModel.delete(note)
        Toast.makeText(this,"${note.id} deleted",Toast.LENGTH_SHORT).show()
    }

    fun saveNote(view: View) {
        val note = inputNote.text.toString()
        if(note.isNotEmpty()){
            viewModel.insert(Note(note))
            Toast.makeText(this,"$note inserted",Toast.LENGTH_SHORT).show()
        }
    }
}