package com.terra.gohere.controller

import com.terra.gohere.dto.Category
import com.terra.gohere.dto.PlaceDto
import com.terra.gohere.dto.PlaceSaveDto
import com.terra.gohere.model.Place
import com.terra.gohere.service.PlaceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/places")
class PlaceController (
        @Autowired val placeService: PlaceService
){

    @GetMapping
    fun places(request: HttpServletRequest): List<Category> {
        return placeService.getAllPlaces(request.remoteAddr)
    }

    @PostMapping
    fun save(@RequestBody placeSaveDto: PlaceSaveDto) {
        placeService.save(Place(placeSaveDto))
    }

    @PostMapping("/{id}")
    fun update(@PathVariable("id") id: String, @RequestBody placeSaveDto: PlaceSaveDto) {
        placeService.save(Place(id, placeSaveDto))
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
                "image",
                28.0
        )

    }

}