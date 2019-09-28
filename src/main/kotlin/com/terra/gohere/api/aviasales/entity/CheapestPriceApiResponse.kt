package com.terra.gohere.api.aviasales.entity

data class CheapestPriceApiResponse (
        val success: Boolean,
        var data: Map<String, Price>,
        var error: String
)