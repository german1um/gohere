package com.terra.gohere.api.aviasales

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

@PropertySource("classpath:token.properties")
@Component
class AviasalesApi (
        @Autowired
        val env: Environment
) {

    private val logger = LoggerFactory.getLogger(AviasalesApi::class.java)

    private var token: String = env.getProperty("aviasales_token")?:""

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.travelpayouts.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val api: AviasalesApiService


    init {
        api = retrofit.create(AviasalesApiService::class.java)
    }

    fun getPrices(origin: String, destination: String): List<Price> {
        val response = api.prices(
                auth = token,
                origin = origin,
                destination = destination
        ).execute()

        val apiResponse = response.body() ?: throw Exception("запрос не прошел почему-то")

        if (apiResponse.data.isEmpty()) {
            logger.info(apiResponse.error)
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST, apiResponse.error)
        }
        return apiResponse.data
    }

    fun whereAmI(ip: String): UserIATA {
        val response = api.whereAmI(
                auth = token,
                ip = ip
        ).execute()
        return response.body() ?: UserIATA("LED", "Saint-Petersburg")
    }

}