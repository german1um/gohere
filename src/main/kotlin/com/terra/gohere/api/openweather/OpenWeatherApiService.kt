package com.terra.gohere.api.openweather

import com.terra.gohere.api.openweather.entity.WeatherApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApiService {
    @GET("data/2.5/weather")
    fun weatherByCoordinates(
            @Query("appid") token: String,
            @Query("lat") lat: Double,
            @Query("lon") lng: Double
    ): Call<WeatherApiResponse>


    @GET("data/2.5/weather")
    fun weatherByCityName(
            @Query("appid") token: String,
            @Query("q") city: String
    ): Call<WeatherApiResponse>
}