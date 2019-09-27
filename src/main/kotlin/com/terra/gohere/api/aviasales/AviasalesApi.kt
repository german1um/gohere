package com.terra.gohere.api.aviasales

import com.terra.gohere.api.aviasales.entity.PriceApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@PropertySource("classpath:token.properties")
@Component
class AviasalesApi (
        @Autowired
        val env: Environment
) {



    private var token: String = env.getProperty("aviasales_token")?:""

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.travelpayouts.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val api: AviasalesApiService


    init {
        api = retrofit.create(AviasalesApiService::class.java)
    }

    fun getPrices(airport: String): PriceApiResponse {
        val response = api.prices(
                auth = token,
                destination = airport
        ).execute()

        return response.body()?: throw Exception("запрос не прошел почему-то")
    }

}