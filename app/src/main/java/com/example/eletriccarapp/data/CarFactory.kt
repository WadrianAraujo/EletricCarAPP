package com.example.eletriccarapp.data

import com.example.eletriccarapp.domain.Car

object CarFactory {
    val list = listOf<Car>(
        Car(
            id = 1,
            price = "R$ 300.000,00",
            power = "200cv",
            battery = "800kWh",
            charger = "10 min",
            urlPhoto = "google.com"
        ),
        Car(
            id = 2,
            price = "R$ 200.000,00",
            power = "605cv",
            battery = "100kWh",
            charger = "20 min",
            urlPhoto = "google.com"
        ),
        Car(
            id = 3,
            price = "R$ 100.000,00",
            power = "310cv",
            battery = "320kWh",
            charger = "30 min",
            urlPhoto = "google.com"
        )
    )
}