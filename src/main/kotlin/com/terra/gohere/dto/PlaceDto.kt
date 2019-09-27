package com.terra.gohere.dto

data class PlaceDto(
        val id: String,
        val name: String,
        val description: String,
        val airport: String,
        val price: Double,
        val video: String,
        val temperature: Double
)