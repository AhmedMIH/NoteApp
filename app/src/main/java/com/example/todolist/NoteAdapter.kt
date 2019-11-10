package com.example.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.model.noteModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.todo_list_row.view.*

class NoteAdapter (private var note:List<noteModel>, private val context: Context): RecyclerView.Adapter<NoteHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_list_row, parent, false)
        return NoteHolder(view)
    }

    override fun getItemCount(): Int=note.size

    override fun onBindViewHolder(holder    : NoteHolder, position: Int) {
        holder.onBind(note[position],context)
    }

}

class NoteHolder (private val view: View):RecyclerView.ViewHolder(view) {
    fun onBind(note: noteModel,context: Context){
        val noteTitle=itemView.findViewById<TextView>(R.id.todoTitle)
        val noteArticle=itemView.findViewById<TextView>(R.id.todoArticle)
        noteArticle.text=note.article
        noteTitle.text=note.titel
        view.deleteButton.setOnClickListener {
            deleteItemFromDB(note,context)
        }
        view.editButton.setOnClickListener {
            updateItemFromDB(note,context)
        }
    }

    private fun updateItemFromDB(note: noteModel,context: Context) {
        (context as MainActivity).moveToAddActivity(note)
    }

    private fun deleteItemFromDB(note: noteModel, context: Context){
        val dbManager= DbManager(context)
        val selectionArgs= arrayOf(note.id.toString())
        dbManager.Delete("ID=?",selectionArgs)
        (context as MainActivity).listNote.clear()
        context.delete()
        context.refreshData()
    }
}
