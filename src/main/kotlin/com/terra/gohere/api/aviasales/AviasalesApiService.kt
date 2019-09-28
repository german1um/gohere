package com.terra.gohere.api.aviasales

import com.terra.gohere.api.aviasales.entity.PriceApiResponse
import com.terra.gohere.api.aviasales.entity.UserIATA
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import retrofit2.Call
import retrofit2.http.*

interface AviasalesApiService {

    @GET("v2/prices/latest")
    fun prices(
            @Header("X-Access-Token") auth: String,
            @Query("currency") currency: String = "usd",
            @Query("origin") origin: String = "LED",
            @Query("destination") destination: String = "TYO",
            @Query("period_type") periodType: String = "year",
            @Query("one_way") oneWay: Boolean = true,
            @Query("page") page: Int = 1,
            @Query("limit") limit: Int = 30,
            @Query("show_to_affiliates") showToAffiliates: Boolean = true,
            @Query("sorting") sorting: String = "price"
            ): Call<PriceApiResponse>

    @GET("whereami")
    fun whereAmI(
            @Header("X-Access-Token") auth: String,
            @Query("locale") locale: String = "en",
            @Query("callback") callback: String = "useriata",
            @Query("ip") ip: String
            ): Call<UserIATA>

}