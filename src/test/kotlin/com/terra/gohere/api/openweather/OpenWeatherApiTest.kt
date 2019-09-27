package com.terra.gohere.api.openweather

import org.junit.Test

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class OpenWeatherApiTest {

    @Autowired
    lateinit var weatherApi: OpenWeatherApi


    @Test
    fun getWeather() {
        val weather = weatherApi.getWeatherByCoordinates(30.292260, 59.932595)
        assert(weather.main.temp > 0)
    }
}