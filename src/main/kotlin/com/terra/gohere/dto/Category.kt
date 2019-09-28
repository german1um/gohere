package com.terra.gohere.dto

import java.util.*

data class Category (
        val name: String = Random().nextInt().toString(),
        val places: List<PlaceDto>
)