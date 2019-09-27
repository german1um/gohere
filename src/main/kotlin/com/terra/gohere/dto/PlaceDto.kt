package com.terra.gohere.dto

import com.terra.gohere.model.Place

data class PlaceDto(
        val id: String,
        val name: String,
        val description: String,
        val airport: String,
        var price: Double = 0.0,
        val video: String,
        var temperature: Double = 0.0
) {
    constructor(place: Place, price: Double, temperature: Double) : this(
            id = place.id,
            name = place.name,
            description = place.description,
            airport = place.airport,
            price = price,
            video = place.video,
            temperature = temperature
    )
}