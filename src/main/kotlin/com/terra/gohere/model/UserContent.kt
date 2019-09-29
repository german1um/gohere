package com.terra.gohere.model

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class UserContent (
        val id: String = UUID.randomUUID().toString(),
        val userId: String,
        val contentLink: String,
        val likesCount: Int = 0
)