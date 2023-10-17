package com.example.eletriccarapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.presentation.adapter.CarAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var btnRedirect: Button
    private lateinit var carList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupListeners()
        setupList()

    }

    fun setupViews() {
        btnRedirect = findViewById(R.id.btn_Redirect)
        carList = findViewById(R.id.rv_list_cars)
    }

    fun setupList() {
        var data = arrayOf(
            "Cupcake", "Donut", "Froyo", "Gingerbread"
        )
        val adapter = CarAdapter(data)
        carList.adapter = adapter
    }

    fun setupListeners() {
        btnRedirect.setOnClickListener {
            startActivity(Intent(this, CalculateAutonomyActivity::class.java))
        }
    }
}