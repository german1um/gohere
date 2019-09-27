package com.terra.gohere.controller

import com.terra.gohere.dto.Category
import com.terra.gohere.dto.PlaceDto
import com.terra.gohere.model.Place
import com.terra.gohere.service.PlaceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/places")
class PlaceController (
        @Autowired val placeService: PlaceService
){

    @GetMapping
    fun places(): List<Category> {
        return placeService.getAllPlaces()
    }

    @PostMapping
    fun save(@RequestBody place: Place) {
        placeService.save(place)
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