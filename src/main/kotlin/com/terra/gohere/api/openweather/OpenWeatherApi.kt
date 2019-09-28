package com.terra.gohere.api.openweather

import com.terra.gohere.api.aviasales.AviasalesApi
import com.terra.gohere.api.openweather.entity.WeatherApiResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@PropertySource("classpath:token.properties")
@Component
class OpenWeatherApi (
        @Autowired
        val env: Environment

){

    private val logger = LoggerFactory.getLogger(AviasalesApi::class.java)

    private var token: String = env.getProperty("openweather_token")?:""

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val api: OpenWeatherApiService


    init {
        api = retrofit.create(OpenWeatherApiService::class.java)
    }

    fun getWeatherByCoordinates(lat: Double, lng: Double): WeatherApiResponse {
        val response = api.weatherByCoordinates(
                token = token,
                lat = lat,
                lng = lng
        ).execute()

        return response.body()?: throw Exception("запрос не прошел почему-то")
    }

    fun getWeatherByCity(city: String): Double {
        val response = api.weatherByCityName(
                token = token,
                city = city
        ).execute()

        if(response.body() == null) {
            logger.info("token = $token city = $city")
        }

        return response.body()?.main?.temp?:273.15
    }

}