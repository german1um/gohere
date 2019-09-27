package com.terra.gohere.dto

data class Category (
        val name: String = "default",
        val places: List<PlaceDto> = listOf(
                PlaceDto(
                        "-1",
                        "Tokyo",
                        "Best city",
                        "TYO",
                        100.0,
                        "https://youtu.be/6qGiXY1SB68",
                        28.0
                )
        )
)