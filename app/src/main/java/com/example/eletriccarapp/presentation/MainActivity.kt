package com.example.eletriccarapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.eletriccarapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var price : EditText
    private lateinit var kmTraveled : EditText
    private lateinit var btnRedirect : Button
    private lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupListeners()
    }

    fun setupViews(){
        btnRedirect = findViewById(R.id.btn_Redirect)
        //kmTraveled = findViewById(R.id.et_km)
        //price = findViewById(R.id.et_price)
        //result = findViewById(R.id.tv_result)
    }

    fun setupListeners(){
        btnRedirect.setOnClickListener {
            startActivity(Intent(this,CalculateAutonomyActivity::class.java))
        }
    }

    fun Calculate(){
        val textPrice = price.text.toString().toFloat()
        val textTraveled = kmTraveled.text.toString().toFloat()
        val result0 = textPrice / textTraveled
        result.text = result0.toString()
    }
}