package com.example.eletriccarapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.domain.Car

class CarAdapter(private val cars: List<Car>) :
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    var carItemLister: (Car) -> Unit = {}

    // Cria uma nova view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return ViewHolder(view)
    }

    // Retorna a quantidade de itens na lista
    override fun getItemCount(): Int = cars.size

    // Pega o conteudo da view e troca pela informacao de item de uma lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.price.text = cars[position].preco
        holder.battery.text = cars[position].bateria
        holder.power.text = cars[position].potencia
        holder.charger.text = cars[position].recarga
        holder.favorite.setOnClickListener {
            val car = cars[position]
            carItemLister(cars[position])
            setupFavorite(car, holder)
        }
    }

    private fun setupFavorite(
        car: Car,
        holder: ViewHolder
    ) {
        car.isFavorite = !car.isFavorite

        if (car.isFavorite) {
            holder.favorite.setImageResource(R.drawable.ic_start_selected)
        } else {
            holder.favorite.setImageResource(R.drawable.ic_start)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val price: TextView
        val battery: TextView
        val power: TextView
        val charger: TextView
        val favorite: ImageView

        init {
            favorite = view.findViewById(R.id.iv_favorites)
            price = view.findViewById(R.id.tv_price_value)
            battery = view.findViewById(R.id.tv_battery_value)
            power = view.findViewById(R.id.tv_power_value)
            charger = view.findViewById(R.id.tv_charger_value)
        }

    }

}

