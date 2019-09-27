package com.terra.gohere.service

import com.terra.gohere.api.aviasales.AviasalesApi
import com.terra.gohere.api.aviasales.entity.Price
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlaceService (
        @Autowired val aviasalesApi: AviasalesApi
){


    fun getPrice(destination: String): Price{
        return aviasalesApi.getPrices(destination).data.first()
    }

}