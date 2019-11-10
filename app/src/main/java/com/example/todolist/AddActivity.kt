package com.example.todolist

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_add.*
import java.lang.Exception

class AddActivity : AppCompatActivity() {
    var id=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val title = findViewById<TextInputEditText>(R.id.EdTitle)
        val des = findViewById<TextInputEditText>(R.id.EdDes)
        Done_button.setOnClickListener {
            if (title.text!!.isEmpty() || des.text!!.isEmpty()) {
                Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show()
            } else {
                insertItemToDB(title,des)
            }
            finish()
        }
        try {
            var bundle:Bundle= intent.extras!!
            id=bundle.getInt("ID")
            EdTitle.setText(bundle.getString("Title"))
            EdDes.setText(bundle.getString("Des"))
        }catch (ex: Exception){}

    }


    private fun insertItemToDB(title:TextInputEditText,des:TextInputEditText){

        val dbManager = DbManager(this)
        val values = ContentValues()
        values.put("Title", title.text.toString())
        values.put("Description", des.text.toString())
        if (id==0){
            val ID = dbManager.Insert(values)
        }
        else{
            val selectionArgs= arrayOf(id.toString())
            val ID=dbManager.Update(values,"ID=?",selectionArgs)
        }
    }

}
