package com.example.workoutapp

import android.icu.math.BigDecimal
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.RadioGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_empty.*
import kotlinx.android.synthetic.main.activity_lets_check_bmi.*
import java.math.RoundingMode

class LetsCheckBmi : AppCompatActivity() {


    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = "US_UNIT_VIEW"
    }

    private var currentVisibleView : String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lets_check_bmi)

        setSupportActionBar(toolbar_lets_check_bmi)
        val actionbar = supportActionBar

        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.title = "Calculate your BMI"

        toolbar_lets_check_bmi.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricsUnitView()

        rgUnits.setOnCheckedChangeListener{ radioGroup:RadioGroup , checkedId:Int->

            if(checkedId == R.id.rbMetricUnits){
                makeVisibleMetricsUnitView()
            }else{
                makeVisibleUsUnitView()
            }

        }

        btnCalculateUnits.setOnClickListener {

            if(isValid()){
                val height: Float
                val weight: Float
                if( currentVisibleView == METRIC_UNITS_VIEW){
                    height = etMetricUnitHeight.text.toString().toFloat()/100
                    weight = etMetricUnitWeight.text.toString().toFloat()
                    val myBMI = weight /(height*height)

                    displayBMIResult(myBMI)
                }else{

                    weight = etUsUnitWeight.text.toString().toFloat()
                    height = etUsUnitHeightFeet.text.toString().toFloat()*12 + etUsUnitHeightInch.text.toString().toFloat()

                    val myBMI =( weight / (height*height) ) * 703
                    displayBMIResult(myBMI)
                }

            }else{
                Snackbar.make(llUnitsview,"Bhau dhang se daal sab kuch",
                    3000).show()
            }
        }





    }

    private fun isValid(): Boolean{

        var valid: Boolean = true

        if(currentVisibleView == METRIC_UNITS_VIEW){
            if(etMetricUnitHeight.text.toString().isEmpty()   ){
                valid = false
            }else if(etMetricUnitWeight.text.toString().isEmpty()){
                valid = false
            }
            return valid
        }else{

            if(etUsUnitHeightFeet.text.toString().isEmpty()   ){
                valid = false
            }else if(etUsUnitHeightInch.text.toString().isEmpty()){
                valid = false
            }else if(etUsUnitWeight.text.toString().isEmpty()){
                valid = false
            }

            return valid
        }

    }

    private fun  displayBMIResult(bmi: Float){

        val bmiLabel: String
        val bmiDescription: String


        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(
                bmi,
                30f
            ) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        tvYourBMI.visibility = View.VISIBLE
        tvBMIValue.visibility = View.VISIBLE
        tvBMIType.visibility = View.VISIBLE
        tvBMIDescription.visibility = View.VISIBLE
        val bmiValue = bmi.toBigDecimal().setScale(2,RoundingMode.UP).toDouble().toString()

        tvBMIValue.text = bmiValue // Value is set to TextView
        tvBMIType.text = bmiLabel // Label is set to TextView
        tvBMIDescription.text = bmiDescription // Description is set to TextView

    }

    private fun makeVisibleMetricsUnitView(){

        currentVisibleView = METRIC_UNITS_VIEW
        llMetricUnitsView.visibility = View.VISIBLE
        llUsUnitsView.visibility = View.GONE

        etMetricUnitHeight.text!!.clear()
        etMetricUnitWeight.text!!.clear()

        tvYourBMI.visibility = View.INVISIBLE
        tvBMIValue.visibility = View.INVISIBLE
        tvBMIDescription.visibility = View.INVISIBLE
        tvBMIType.visibility = View.INVISIBLE

    }

    private fun makeVisibleUsUnitView(){

        currentVisibleView = US_UNITS_VIEW

        llMetricUnitsView.visibility = View.GONE
        llUsUnitsView.visibility = View.VISIBLE

        etUsUnitWeight.text!!.clear()
        etUsUnitHeightFeet.text!!.clear()
        etUsUnitHeightInch.text!!.clear()

        tvYourBMI.visibility = View.INVISIBLE
        tvBMIValue.visibility = View.INVISIBLE
        tvBMIDescription.visibility = View.INVISIBLE
        tvBMIType.visibility = View.INVISIBLE

    }




}