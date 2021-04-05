package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_final.*

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        Toast.makeText(this,"adskfhskajdv.hks",Toast.LENGTH_SHORT).show()
        setSupportActionBar(toolbar_final_activity)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        toolbar_final_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        btnFinish.setOnClickListener {

            val intent  = Intent(this@FinalActivity , MainActivity::class.java)
            startActivity(intent)

        }

    }
}