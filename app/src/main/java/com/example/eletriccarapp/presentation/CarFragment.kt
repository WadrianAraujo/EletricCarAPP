package com.example.eletriccarapp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.CarFactory
import com.example.eletriccarapp.presentation.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CarFragment : Fragment() {
    private lateinit var btnRedirect: FloatingActionButton
    private lateinit var carList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()
        setupListeners()
    }

    fun setupView(view: View){
        btnRedirect = view.findViewById(R.id.fab_calculate)
        carList = view.findViewById(R.id.rv_list_cars)

    }

    fun setupList() {
        val adapter = CarAdapter(CarFactory.list)
        carList.adapter = adapter
    }

    fun setupListeners() {
        btnRedirect.setOnClickListener {
            startActivity(Intent(context, CalculateAutonomyActivity::class.java))
        }
    }
}