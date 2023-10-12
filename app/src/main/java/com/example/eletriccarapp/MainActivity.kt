package com.example.eletriccarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var price : EditText
    private lateinit var kmTraveled : EditText
    private lateinit var btnCalculate : Button
    private lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupListeners()
    }

    fun setupViews(){
        btnCalculate = findViewById(R.id.btn_calculate)
        kmTraveled = findViewById(R.id.et_km)
        price = findViewById(R.id.et_price)
        result = findViewById(R.id.tv_result)
    }

    fun setupListeners(){
        btnCalculate.setOnClickListener {
            Calculate()
        }
    }

    fun Calculate(){
        val textPrice = price.text.toString().toFloat()
        val textTraveled = kmTraveled.text.toString().toFloat()
        val result0 = textPrice / textTraveled
        result.text = result0.toString()
    }
}