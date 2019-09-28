package com.terra.gohere.api.aviasales.entity

data class Flight (
        val origin: String,
        val destination: String,
        val depart_date: String,
        val value: Double
) {
    override fun toString(): String {
        val list = depart_date.split('-')
        val date = list[2].toString() + list[1].toString()
        return "https://www.aviasales.ru/search/$origin$date${destination}1"
    }
}
