package com.terra.gohere.controller

import com.terra.gohere.dto.Category
import com.terra.gohere.dto.PlaceDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/places")
class PlaceController {

    @GetMapping
    fun places(): List<Category> {
        return listOf<Category>(
                Category(),
                Category(),
                Category()
        )
    }

    @GetMapping("/{id}")
    fun place(@PathVariable("id") id: String): PlaceDto {
        return PlaceDto(
                "-1",
                "Tokyo",
                "Best city",
                "TYO",
                100.0,
                "https://youtu.be/6qGiXY1SB68",
                28.0
        )

    }

}