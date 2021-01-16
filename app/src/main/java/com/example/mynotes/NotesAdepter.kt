package com.example.mynotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdepter(private val context:Context, private val listner: onNoteClickListner): RecyclerView.Adapter<NotesAdepter.NoteViewHolder>() {
        private val allNotes = ArrayList<Note>()
    inner class NoteViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val note = itemView.findViewById<TextView>(R.id.note)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deletebutton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listner.onClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
      return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currNote = allNotes[position]
        holder.note.text = currNote.text
    }
    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}
interface onNoteClickListner{
    fun onClicked(note:Note)
}