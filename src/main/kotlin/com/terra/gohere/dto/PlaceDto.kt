package com.terra.gohere.dto

import com.terra.gohere.api.aviasales.entity.Flight
import com.terra.gohere.model.Place

data class PlaceDto(
        val id: String,
        val name: String,
        val description: String,
        val airport: String,
        val flightLink: String,
        val date: String,
        val price: Double,
        val video: String,
        val image: String,
        var temperature: Double = 0.0
) {
    constructor(place: Place, flightLink: String, date: String, price: Double, temperature: Double) : this(
            id = place.id,
            name = place.name,
            description = place.description,
            airport = place.airport,
            flightLink = flightLink,
            date = date,
            price = price,
            video = place.video,
            image = place.image,
            temperature = temperature
    )
}