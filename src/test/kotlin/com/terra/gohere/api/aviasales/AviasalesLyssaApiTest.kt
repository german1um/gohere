package com.terra.gohere.api.aviasales

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class AviasalesLyssaApiTest {
    @Autowired
    lateinit var aviasalesLyssaApi: AviasalesLyssaApi

    @Test
    fun getCheapestPricesForMonth() {
        val cheapestPricesForMonth = aviasalesLyssaApi.getCheapestPricesForMonth("LED", "TYO", "2019-11-01")
        println(cheapestPricesForMonth)
    }
}