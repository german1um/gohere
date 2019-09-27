package com.terra.gohere.dto

data class PlaceDto(
        val id: String,
        val name: String,
        val description: String,
        val tickets: String,
        val price: Double,
        val video: String,
        val temperature: Double
)