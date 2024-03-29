package com.terra.gohere.controller

import com.terra.gohere.dto.Category
import com.terra.gohere.dto.PlaceSaveDto
import com.terra.gohere.model.Place
import com.terra.gohere.service.PlaceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.Month
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

    @DeleteMapping()
    fun dropPlaces() {
        placeService.dropPlaces()
    }

    @PutMapping("/{id}")
    fun put(@PathVariable("id") id: String, @RequestBody placeSaveDto: PlaceSaveDto) {
        placeService.save(Place(id, placeSaveDto))
    }

    @PatchMapping("/{id}")
    fun patch(@PathVariable("id") id: String,
              @RequestParam video: String,
              @RequestParam image: String
              ) {
        placeService.patch(
                id, video, image
        )
    }

    @PatchMapping("/{id}/seasons")
    fun patchSeasons(@PathVariable("id") id: String,
              @RequestParam bestSeasons: List<Month>
    ) {
        placeService.patch(
                id, bestSeasons
        )
    }

    @PostMapping("/clearCache")
    fun clearCache() {
        placeService.clearCache()
    }

}