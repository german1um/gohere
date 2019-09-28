package com.terra.gohere.api.aviasales.entity

data class CheapestPriceApiResponse (
        val success: Boolean,
        var data: List<Price>,
        var error: String
)