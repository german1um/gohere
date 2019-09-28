package com.terra.gohere.service

import com.terra.gohere.api.aviasales.AviasalesApi
import com.terra.gohere.api.openweather.OpenWeatherApi
import com.terra.gohere.dto.Category
import com.terra.gohere.dto.PlaceDto
import com.terra.gohere.model.Place
import com.terra.gohere.repository.PlaceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlaceService (
        @Autowired val placeRepository: PlaceRepository,
        @Autowired val aviasalesApi: AviasalesApi,
        @Autowired val weatherApi: OpenWeatherApi
){

    fun getWeatherByCoordinates(lat: Double, lng: Double): Double{
        return weatherApi.getWeatherByCoordinates(lat, lng).main.temp - 273.15
    }

    fun getWeatherByCity(city: String): Double{
        return weatherApi.getWeatherByCity(city).main.temp - 273.15
    }

    fun getPrice(origin: String, destination: String): Double{
        return aviasalesApi.getPrices(origin, destination).first().value
    }

    fun getAllPlaces(remoteAddr: String): List<Category> {
        val origin = getUserIATA(remoteAddr)
        return placeRepository.findAll().groupBy {
            it.category
        }.map {
            Category(
                    name = it.key,
                    places = it.value.map { place ->
                        PlaceDto(
                                place,
                                getPrice(origin, place.airport),
                                getWeatherByCity(place.name)
                        )
                    }
            )
        }
    }

    private fun getUserIATA(ip: String): String {
        return aviasalesApi.whereAmI(ip).iata
    }

    fun save(place: Place) {
        placeRepository.save(place)
    }

}