package com.example.eletriccarapp.data

import com.example.eletriccarapp.domain.Car

object CarFactory {
    val list = listOf<Car>(
        Car(
            id = 1,
            preco = "R$ 300.000,00",
            potencia = "200cv",
            bateria = "800kWh",
            recarga = "10 min",
            urlPhoto = "google.com"
        ),
        Car(
            id = 2,
            preco = "R$ 200.000,00",
            potencia = "605cv",
            bateria = "100kWh",
            recarga = "20 min",
            urlPhoto = "google.com"
        ),
        Car(
            id = 3,
            preco = "R$ 100.000,00",
            potencia = "310cv",
            bateria = "320kWh",
            recarga = "30 min",
            urlPhoto = "google.com"
        )
    )
}