package com.terra.gohere.controller

import com.terra.gohere.dto.PlaceDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/places")
class PlaceController {

    @GetMapping
    fun places(): List<PlaceDto> {
        return listOf<PlaceDto>(
                PlaceDto(
                        "-1",
                        "Tokyo",
                        "Best city",
                        "https://www.aviasales.ru/search/LED0305TYO09051",
                        100.0,
                        "https://youtu.be/6qGiXY1SB68",
                        28.0
                )
        )
    }
}