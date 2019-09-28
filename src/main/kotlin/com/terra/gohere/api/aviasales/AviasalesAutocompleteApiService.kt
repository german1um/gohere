package com.terra.gohere.api.aviasales

import com.terra.gohere.api.aviasales.entity.*
import retrofit2.Call
import retrofit2.http.*

interface AviasalesAutocompleteApiService {

    @GET("places2")
    fun cityData(
            @Header("X-Access-Token") auth: String,
            @Query("term") iata: String = "LED",
            @Query("locale") locale: String = "en",
            @Query("types[]") types: String = "city"
    ): Call<List<CityName>>


}