package com.terra.gohere.service

import com.terra.gohere.api.aviasales.AviasalesApi
import com.terra.gohere.api.aviasales.AviasalesLyssaApi
import com.terra.gohere.api.aviasales.entity.Flight
import com.terra.gohere.api.openweather.OpenWeatherApi
import com.terra.gohere.dto.Category
import com.terra.gohere.dto.PlaceDto
import com.terra.gohere.model.Place
import com.terra.gohere.repository.PlaceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.Month

@Service
class PlaceService(
        @Autowired val placeRepository: PlaceRepository,
        @Autowired val aviasalesApi: AviasalesApi,
        @Autowired val aviasalesLyssaApi: AviasalesLyssaApi,
        @Autowired val weatherApi: OpenWeatherApi
) {

    fun getWeatherByCoordinates(lat: Double, lng: Double): Double {
        return weatherApi.getWeatherByCoordinates(lat, lng).main.temp - 273.15
    }

    fun getWeatherByCity(city: String): Double {
        return weatherApi.getWeatherByCity(city) - 273.15
    }

    //returns string value of first day of soonest hot month
    fun getSoonestHotMonth(months: List<Month>): String {
        val today = LocalDate.now().monthValue
        val targetMonth = months.map { it.value }.minBy {
            var res: Int = if (it >= today) {
                it - today
            } else {
                (12 - today) + it
            }
            res
        }!!

        val targetYear = if (now().monthValue <= targetMonth) {
            now().year
        } else {
            now().year + 1
        }

        return LocalDate.of(targetYear, targetMonth, 1).toString()
    }

    fun getFlight(origin: String, destination: String, month: String): Flight {
        return aviasalesLyssaApi.getCheapestPricesForMonth(origin, destination, month)
                .minBy { it.value }?:
        throw ResponseStatusException(
                HttpStatus.I_AM_A_TEAPOT,
                "method getFlight origin = $origin, destination = $destination month = $month"
        )
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
                                getFlight(origin, place.airport, getSoonestHotMonth(place.bestSeasons)),
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

    fun dropPlaces() {
        placeRepository.deleteAll()
    }

}