package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_final.*
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setSupportActionBar(toolbar_history_activity)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.title = "History"
        toolbar_history_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        getAllTheData()
    }

    private fun getAllTheData(){
        val db = dbHandler(this,null)
        val allDates = db.readDatabase()
        if(allDates.size >0){

            tvHistory.visibility = View.VISIBLE
            rvHistory.visibility = View.VISIBLE
            tvNoDataAvailable.visibility = View.GONE

            rvHistory.layoutManager = LinearLayoutManager(this)
            val hisAdapter = dbAdapter(this, allDates)
            rvHistory.adapter = hisAdapter

        }else{
            tvHistory.visibility = View.GONE
            rvHistory.visibility = View.GONE
            tvNoDataAvailable.visibility = View.VISIBLE
        }

    }







}