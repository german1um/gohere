package com.terra.gohere.service

import com.terra.gohere.api.aviasales.AviasalesApi
import com.terra.gohere.api.aviasales.AviasalesAutocompleteApi
import com.terra.gohere.api.aviasales.AviasalesLyssaApi
import com.terra.gohere.api.aviasales.entity.Flight
import com.terra.gohere.api.openweather.OpenWeatherApi
import com.terra.gohere.dto.Category
import com.terra.gohere.dto.PlaceDto
import com.terra.gohere.model.CachedData
import com.terra.gohere.model.Place
import com.terra.gohere.repository.CachedDataRepository
import com.terra.gohere.repository.PlaceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.LocalDateTime
import java.time.Month

@PropertySource("classpath:token.properties")
@Service
class PlaceService(
        @Autowired val placeRepository: PlaceRepository,
        @Autowired val cachedDataRepository: CachedDataRepository,
        @Autowired val aviasalesApi: AviasalesApi,
        @Autowired val aviasalesLyssaApi: AviasalesLyssaApi,
        @Autowired val aviasalesAutocompleteApi: AviasalesAutocompleteApi,
        @Autowired val weatherApi: OpenWeatherApi,
        @Autowired val env: Environment
) {

    fun getWeatherByCoordinates(lat: Double, lng: Double): Double {
        return weatherApi.getWeatherByCoordinates(lat, lng).main.temp - 273.15
    }

    fun getWeatherByCity(iata: String): Double {
        val city = aviasalesAutocompleteApi.cityName(iata)
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

    fun isCacheStale(timestamp: LocalDateTime): Boolean {
        return Duration.between(LocalDateTime.now(), timestamp).toDays() > 1
    }

    fun getFlightCached(place: Place, remoteAddr: String): Flight {
        return cachedDataRepository.findByPlaceId(place.id).flight
    }

    fun getFlight(origin: String, destination: String, month: String): Flight {
        return aviasalesLyssaApi.getCheapestPricesForMonth(origin, destination, month)
                .minBy { it.value } ?: throw ResponseStatusException(
                HttpStatus.I_AM_A_TEAPOT,
                "method getFlight origin = $origin, destination = $destination month = $month"
        )
    }

    fun updateCache(place: Place, remoteAddr: String): Place {
        if (cachedDataRepository.existsByPlaceId(place.id)) {
            val cachedData = cachedDataRepository.findByPlaceId(place.id)
            if (isCacheStale(cachedData.timestamp)) {
                val flight = getFlight(
                        origin = getUserIATA(remoteAddr),
                        destination = place.airport,
                        month = getSoonestHotMonth(place.bestSeasons)
                )
                cachedDataRepository.save(
                        CachedData(
                                id = cachedData.id,
                                placeId = cachedData.placeId,
                                flight = flight,
                                temperature = getWeatherByCity(place.airport),
                                timestamp = LocalDateTime.now()
                        )
                )
            }
        } else {
            val flight = getFlight(
                    origin = getUserIATA(remoteAddr),
                    destination = place.airport,
                    month = getSoonestHotMonth(place.bestSeasons)
            )
            cachedDataRepository.save(
                    CachedData(
                            placeId = place.id,
                            flight = flight,
                            temperature = getWeatherByCity(place.airport),
                            timestamp = LocalDateTime.now()
                    )
            )
        }
        return place
    }

    fun getAllPlaces(remoteAddr: String): List<Category> {
        return placeRepository.findAll().map {
            updateCache(it, remoteAddr)
        }.groupBy {
            it.category
        }.map {
            Category(
                    name = it.key,
                    places = it.value.map { place ->
                        val flight = getFlightCached(place, remoteAddr)
                        PlaceDto(
                                place,
                                getFlightLink(flight),
                                flight.depart_date,
                                flight.value,
                                getWeatherByCityCached(place)
                        )
                    }
            )
        }
    }

    private fun getWeatherByCityCached(place: Place): Double {
        return cachedDataRepository.findByPlaceId(place.id).temperature
    }


    private fun getUserIATA(ip: String): String {
        return aviasalesApi.whereAmI(ip).iata
    }

    fun save(place: Place) {
        placeRepository.save(place)
    }

    fun patch(id: String,
              video: String,
              image: String) {
        val place: Place = placeRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Place with such id not found")
        }
        placeRepository.save(
                Place(
                        id = place.id,
                        name = place.name,
                        description = place.description,
                        airport = place.airport,
                        video = video,
                        image = image,
                        category = place.category,
                        bestSeasons = place.bestSeasons
                )
        )
    }

    fun patch(id: String, bestSeasons: List<Month>) {
        val place: Place = placeRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Place with such id not found")
        }
        placeRepository.save(
                Place(
                        id = place.id,
                        name = place.name,
                        description = place.description,
                        airport = place.airport,
                        video = place.video,
                        image = place.image,
                        category = place.category,
                        bestSeasons = bestSeasons
                )
        )
    }

    fun dropPlaces() {
        placeRepository.deleteAll()
    }

    fun clearCache() {
        cachedDataRepository.deleteAll()
    }

    fun getFlightLink(flight: Flight): String {
        val list = flight.depart_date.split('-')
        val date = list[2] + list[1]
        val referral = env.getProperty("aviasales_referral")?:""
        return "https://www.aviasales.ru/search/${flight.origin}$date${flight.destination}1$referral"
    }

}