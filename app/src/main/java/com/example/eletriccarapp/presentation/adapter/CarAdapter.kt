package com.example.eletriccarapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.domain.Car

class CarAdapter(private val cars: List<Car>) :
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
        holder.price.text = cars[position].price
        holder.battery.text = cars[position].battery
        holder.power.text = cars[position].power
        holder.charger.text = cars[position].charger
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val price: TextView
        val battery: TextView
        val power: TextView
        val charger: TextView

        init {
            price = view.findViewById(R.id.tv_price_value)
            battery = view.findViewById(R.id.tv_battery_value)
            power = view.findViewById(R.id.tv_power_value)
            charger = view.findViewById(R.id.tv_charger_value)
        }

    }

}

