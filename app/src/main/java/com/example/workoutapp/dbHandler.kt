package com.example.workoutapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class dbHandler(context: Context ,factory: SQLiteDatabase.CursorFactory? ):
        SQLiteOpenHelper(context, DATABASE_NAME ,factory, DATABASE_VERSION) {

    companion object{
        private val DATABASE_VERSION  = 1
        private val DATABASE_NAME = "WorkoutApp.db"
        //table details
        private val TABLE_HISTORY = "history" // table name
        private val COLUMN_ID = "_id"
        private val COLUMN_COMPLETED_DATE = "complete_date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val creator:String = "CREATE TABLE " + TABLE_HISTORY + "(" + COLUMN_ID + " INTEGER PRIMARY KEY ," +
                COLUMN_COMPLETED_DATE + " TEXT );"
        db!!.execSQL(creator)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val s: String = "DROP TABLE IF EXIST "+ TABLE_HISTORY
        db!!.execSQL(s)
        onCreate(db)
    }

    fun addDate(date: String){
        val conVal = ContentValues() // kinda empty array to keep the  values
        conVal.put(COLUMN_COMPLETED_DATE , date)
        val db = this.writableDatabase
        db.insert(TABLE_HISTORY,null,conVal)
        db.close()
    }

    fun readDatabase() : ArrayList<String> {
        val list = ArrayList<String>()
        val db = this.readableDatabase
        var cursor = db.rawQuery("SELECT * FROM $TABLE_HISTORY " , null)
        while(cursor.moveToNext() ){

            val dateVal = cursor.getString( cursor.getColumnIndex(COLUMN_COMPLETED_DATE))
            list.add(dateVal)

        }
        cursor.close()
        return list

    }

}