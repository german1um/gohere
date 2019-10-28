package com.terra.gohere.api.aviasales.entity

data class Flight (
        val origin: String,
        val destination: String,
        val depart_date: String,
        val value: Double
)
