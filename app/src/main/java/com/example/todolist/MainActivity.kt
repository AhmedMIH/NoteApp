package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.model.noteModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
     var listNote= ArrayList<noteModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFromDB()
    }
    override fun onRestart() {
        super.onRestart()
        refreshData()
    }

    fun Move(view: View) {
        val i=Intent(this@MainActivity,AddActivity::class.java)
        startActivity(i)
    }
    fun moveToAddActivity(note: noteModel){
        val intent=Intent(this@MainActivity,AddActivity::class.java)
        intent.putExtra("ID",note.id)
        intent.putExtra("Title",note.titel)
        intent.putExtra("Des",note.article)
        startActivity(intent)
    }
    private fun loadFromDB(title:String="%"){
        val dbManager= DbManager(this)
        val projection= arrayOf("ID","Title","Description")
        val selectionArgs= arrayOf(title)
        val cursor=dbManager.Query(projection,"Title like ?",selectionArgs,"Title")
        if(cursor.moveToFirst()){
            do {
                val iD=cursor.getInt(cursor.getColumnIndex("ID"))
                val title=cursor.getString(cursor.getColumnIndex("Title"))
                val description=cursor.getString(cursor.getColumnIndex("Description"))
                insertToRecyclerView(iD, title, description)
            }while (cursor.moveToNext())
        }
    }
    private fun insertToRecyclerView(iD:Int, title: String, description:String){
        listNote.add(noteModel(iD,title,description))
        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        val adapter=NoteAdapter(listNote,this)
        recyclerView!!.adapter= adapter
    }
    fun refreshData(){
        listNote.clear()
        loadFromDB()
    }
    fun delete(){
        recyclerView.adapter=null
    }
}
