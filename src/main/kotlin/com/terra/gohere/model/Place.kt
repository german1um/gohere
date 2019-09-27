package com.terra.gohere.model

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Place(
        val id: String = UUID.randomUUID().toString(),
        val name: String,
        val description: String,
        val video: String
)
