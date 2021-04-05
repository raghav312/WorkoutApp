package com.example.workoutapp

import android.app.Dialog
import android.content.Intent
import android.media.MediaParser
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.MediaStore
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_empty.*
import kotlinx.android.synthetic.main.dialog_check_quit.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

class EmptyActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0 //0 to 10

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0 //0 to 30
    private var exerciseTimerDuration: Long = 5

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private var exerciseAdapter: ExerciseAdapter ? = null


    private var tts:TextToSpeech? = null

    private var player : MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        setSupportActionBar(toolbar_empty_activity)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        toolbar_empty_activity.setNavigationOnClickListener {
            customDialogForQuit()
        }

        exerciseList = Constants.defaultExerciseList()
        tts = TextToSpeech(this,this)

        setupRestView()

        exerciseStatusRecyclerView()

    }

    override fun onDestroy() {
        if(restTimer!= null){
            restTimer!!.cancel()
            restProgress = 0
        }

        if(exerciseTimer!= null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()

        }
        if(player != null){
            player!!.stop()

        }

        super.onDestroy()


    }

    private fun setRestProgressBar(){


        progessBar.progress = restProgress
        restTimer = object :CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progessBar.progress = 10-restProgress
                tvTimer.text = (10-restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                setupExerciseView()

            }

        }.start()

    }

    private fun setExerciseProgressBar(){

        progessBar2.progress = exerciseProgress

        exerciseTimer = object :CountDownTimer(exerciseTimerDuration * 1000,1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                progessBar2.progress = exerciseTimerDuration.toInt()-exerciseProgress
                tvTimer2.text = (exerciseTimerDuration.toInt()-exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePosition < exerciseList!!.size -1 ){
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupRestView()
                }else {
                    finish()
                    val i1 = Intent(this@EmptyActivity,FinalActivity::class.java)
                    startActivity(i1)
                }
            }

        }.start()

    }

    private fun setupRestView(){

        try{
            var soundURI = Uri.parse("android.resource://com.example.workoutapp/"+ R.raw.press_start)
            player = MediaPlayer.create(applicationContext, soundURI)
            player!!.isLooping = false
            player!!.start()

        }catch (e: Exception){
            e.printStackTrace()
            Toast.makeText(this,"exception aa gya bhau ",
            Toast.LENGTH_SHORT).show()
        }

        llRestView.visibility= View.VISIBLE
        llExerciseView.visibility = View.GONE

        if(restTimer!= null){
            restTimer!!.cancel()
            restProgress = 0
        }

        whatsNextExercise.text = exerciseList!![currentExercisePosition+1].getName()


        setRestProgressBar()
    }

    private fun setupExerciseView(){

        llRestView.visibility = View.GONE
        llExerciseView.visibility = View.VISIBLE
        if(exerciseTimer!= null){
            exerciseTimer!!.cancel()
            exerciseProgress =0
        }
        setExerciseProgressBar()
        speakup(whatsNextExercise.text.toString())
        ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        tvExerciseName.setText(exerciseList!![currentExercisePosition].getName())
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)

            if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this,"The Language specified is not supported!",Toast.LENGTH_SHORT).show()

            }

        }else{
            Toast.makeText(this,"Initialization Failed",Toast.LENGTH_SHORT).show()
        }

    }

    private fun speakup(text:String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }


    private fun exerciseStatusRecyclerView(){
        rvExerciseStatus.layoutManager = LinearLayoutManager(this
        , LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseAdapter(this,exerciseList!!)
        rvExerciseStatus.adapter = exerciseAdapter
    }

    private fun customDialogForQuit(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_check_quit)
        dialog.dialog_button_yes.setOnClickListener {
            finish()
            dialog.dismiss()
        }
        dialog.dialog_button_no.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

}

