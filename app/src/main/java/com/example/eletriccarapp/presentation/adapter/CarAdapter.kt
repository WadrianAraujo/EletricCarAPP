package com.example.eletriccarapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R

class CarAdapter(private val cars: Array<String>) :
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    // Cria uma nova view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return ViewHolder(view)
    }

    // Retorna a quantidade de itens na lista
    override fun getItemCount(): Int = cars.size

    // Pega o conteudo da view e troca pela informacao de item de uma lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = cars[position]
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView
 
        init {
            text = view.findViewById(R.id.tv_price)
        }

    }

}

