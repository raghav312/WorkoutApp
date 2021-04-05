package com.example.workoutapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ll_start.setOnClickListener{
            val intent = Intent(this, EmptyActivity::class.java )
            startActivity(intent)
        }
        ll_bmi_start.setOnClickListener{

            val intent = Intent(this, LetsCheckBmi::class.java )
            startActivity(intent)

        }

        ll_history_start.setOnClickListener{
            val intent = Intent(this , HistoryActivity::class.java)
            startActivity(intent)
        }



    }

}