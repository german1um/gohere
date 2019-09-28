package com.terra.gohere.model

import com.terra.gohere.dto.PlaceSaveDto
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Place(
        val id: String = UUID.randomUUID().toString(),
        val name: String,
        val description: String,
        val airport: String,
        val video: String,
        val image: String,
        val category: String
) {
    constructor(placeSaveDto: PlaceSaveDto): this(
            name = placeSaveDto.name,
            description = placeSaveDto.description,
            airport = placeSaveDto.airport,
            video = placeSaveDto.video,
            image = placeSaveDto.image,
            category = placeSaveDto.category
    )
}
