package com.terra.gohere.dto

import java.util.*

data class PlaceSaveDto (
        val name: String,
        val description: String,
        val airport: String,
        val video: String,
        val image: String,
        val category: String
)