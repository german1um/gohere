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
class AviasalesAutocompleteApi (
        @Autowired
        val env: Environment
) {

    private val logger = LoggerFactory.getLogger(AviasalesAutocompleteApi::class.java)

    private var token: String = env.getProperty("aviasales_token")?:""

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://autocomplete.travelpayouts.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val api: AviasalesAutocompleteApiService


    init {
        api = retrofit.create(AviasalesAutocompleteApiService::class.java)
    }

    fun cityName(iata: String): String {
        val response = api.cityData(
                auth = token,
                iata = iata
        ).execute()
        return response.body()?.get(0)?.name ?: "Saint-Petersburg"
    }

}