package com.example.todolist

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder

open class DbManager{
    val dbName="MyNotes"
    val dbTable="Notes"
    val colID="ID"
    val colTitle="Title"
    val colDes="Description"
    val dbVersion=2
    val sqlCreateTable="CREATE TABLE IF NOT EXISTS " + dbTable + " ("+ colID +" INTEGER PRIMARY KEY,"+
            colTitle +" TEXT, "+ colDes +" TEXT);"
    var sqlDB:SQLiteDatabase?=null

    constructor(context: Context){
        var db=DatabaseHelperNotes(context)
        sqlDB=db.writableDatabase
    }


   inner class DatabaseHelperNotes : SQLiteOpenHelper {
       var  context:Context?=null
       constructor(context: Context):super(context,dbName,null,dbVersion){
           this.context=context
       }
       override fun onCreate(p0: SQLiteDatabase?) {
            p0!!.execSQL(sqlCreateTable)

        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            p0!!.execSQL("Drop table IF EXISTS $dbTable")
            onCreate(p0)

        }

    }

    fun Insert(values: ContentValues):Long{
        val ID= sqlDB!!.insert(dbTable,"",values)
        return ID
    }

    fun Query(projection: Array<String>,selection:String,selectionArguments: Array<String>,SorOrder:String):Cursor{
        val qb =SQLiteQueryBuilder()
        qb.tables=dbTable
        val cursor=qb.query(sqlDB,projection,selection,selectionArguments,null,null,SorOrder)
        return cursor
    }

    fun Delete(selection:String, selectionArguments: Array<String>):Int{
        return sqlDB!!.delete(dbTable,selection,selectionArguments)
    }

    fun Update(values: ContentValues,selection:String, selectionArguments: Array<String>):Int{
        val count=sqlDB!!.update(dbTable,values,selection,selectionArguments)
        return count
     }
}

