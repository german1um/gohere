package com.terra.gohere.api.aviasales.entity

data class LatestPriceApiResponse (
        val success: Boolean,
        var data: List<Price>,
        var error: String
)