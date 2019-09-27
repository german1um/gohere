package com.terra.gohere.dto

import java.util.*

data class Category (
        val name: String = Random().nextInt().toString(),
        val places: List<PlaceDto> = listOf(
                PlaceDto(
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        100.0,
                        Random().nextInt().toString(),
                        28.0
                ),
                PlaceDto(
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        100.0,
                        Random().nextInt().toString(),
                        28.0
                ),
                PlaceDto(
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        100.0,
                        Random().nextInt().toString(),
                        28.0
                ),
                PlaceDto(
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        100.0,
                        Random().nextInt().toString(),
                        28.0
                ),
                PlaceDto(
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        100.0,
                        Random().nextInt().toString(),
                        28.0
                ),
                PlaceDto(
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        100.0,
                        Random().nextInt().toString(),
                        28.0
                ),
                PlaceDto(
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        Random().nextInt().toString(),
                        100.0,
                        Random().nextInt().toString(),
                        28.0
                )
        )
)