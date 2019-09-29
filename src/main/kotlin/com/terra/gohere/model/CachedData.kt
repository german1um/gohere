package com.terra.gohere.model

import com.terra.gohere.api.aviasales.entity.Flight
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class CachedData (
        val id: String = UUID.randomUUID().toString(),
        val placeId: String,
        val flight: Flight,
        var temperature: Double = 0.0,
        val timestamp: LocalDateTime
)
