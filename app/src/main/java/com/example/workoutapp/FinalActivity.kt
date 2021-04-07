package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_final.*
import java.text.SimpleDateFormat
import java.util.*

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        setSupportActionBar(toolbar_final_activity)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        toolbar_final_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        btnFinish.setOnClickListener {
            finish()
        }
        addDateToHistory()

    }

    private fun addDateToHistory(){

        val calender = Calendar.getInstance()
        val dateTime = calender.time
        val simpleDateFormat = SimpleDateFormat("dd MM yyyy HH:mm:ss", Locale.getDefault())
        val date = simpleDateFormat.format(dateTime)
        val db = dbHandler(this, null )
        db.addDate(date)

    }

}