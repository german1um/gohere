package com.terra.gohere.api.aviasales.entity

data class CheapestPriceApiResponseForMonth (
        var month: Map<String, Flight>,
        var error: String
)