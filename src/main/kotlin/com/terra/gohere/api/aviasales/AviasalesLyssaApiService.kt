package com.terra.gohere.api.aviasales

import com.terra.gohere.api.aviasales.entity.CheapestPriceApiResponseForMonth
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate.*

interface AviasalesLyssaApiService {

    @GET("v2/widget/month")
    fun cheapestPricesForMonth(
            @Query("currency") currency: String = "usd",
            @Query("origin_iata") origin: String = "LED",
            @Query("destination_iata") destination: String = "TYO",
            @Query("one_way") oneWay: Boolean = true,
            @Query("depart_month") departMonth: String = now().toString()
            ): Call<CheapestPriceApiResponseForMonth>


}