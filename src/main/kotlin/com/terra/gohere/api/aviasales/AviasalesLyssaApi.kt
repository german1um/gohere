package com.terra.gohere.api.aviasales

import com.terra.gohere.api.aviasales.entity.Flight
import com.terra.gohere.api.aviasales.entity.Price
import com.terra.gohere.api.aviasales.entity.UserIATA
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Component
class AviasalesLyssaApi (
        @Autowired
        val env: Environment
) {

    private val logger = LoggerFactory.getLogger(AviasalesLyssaApi::class.java)

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://lyssa.aviasales.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val api: AviasalesLyssaApiService


    init {
        api = retrofit.create(AviasalesLyssaApiService::class.java)
    }

    fun getCheapestPricesForMonth(origin: String, destination: String, firstDayOfMonth: String): Collection<Flight> {
        val response = api.cheapestPricesForMonth(
                origin = origin,
                destination = destination,
                departMonth = firstDayOfMonth

        ).execute()

        val apiResponse = response.body() ?: throw Exception("origin = $origin destination = $destination firstDayOfMonth=$firstDayOfMonth. Запрос getCheapestPricesForMonth не прошел почему-то")

        if (apiResponse.month.isEmpty()) {
            logger.info(apiResponse.error)
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "origin = $origin destination = $destination.\n" + apiResponse.error)
        }
        return apiResponse.month.values
    }
}