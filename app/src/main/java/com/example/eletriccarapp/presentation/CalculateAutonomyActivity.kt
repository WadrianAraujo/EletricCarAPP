package com.example.eletriccarapp.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eletriccarapp.R

class CalculateAutonomyActivity : AppCompatActivity(){
    private lateinit var result : TextView
    private lateinit var price : EditText
    private lateinit var kmTraveled : EditText
    private lateinit var btnCalculate : Button
    private lateinit var btnClose : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_autonomy)

        setupViews()
        setupListeners()

    }

    fun setupViews(){
        kmTraveled = findViewById(R.id.et_km)
        price = findViewById(R.id.et_price)
        result = findViewById(R.id.tv_result)
        btnCalculate = findViewById(R.id.btn_calculate)
        btnClose = findViewById(R.id.iv_close_img)
    }

    fun setupListeners(){
        btnCalculate.setOnClickListener {
            Calculate()
        }
        btnClose.setOnClickListener {
            finish()
        }
    }

    fun Calculate(){
        val textPrice = price.text.toString().toFloat()
        val textTraveled = kmTraveled.text.toString().toFloat()
        val result0 = textPrice / textTraveled
        result.text = result0.toString()
    }
}