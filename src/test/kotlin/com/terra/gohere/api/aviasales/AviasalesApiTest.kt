package com.terra.gohere.api.aviasales

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
internal class AviasalesApiTest {
    @Autowired lateinit var aviasalesApi: AviasalesApi
    @Test
    fun getPrices() {
        assert(aviasalesApi.getPrices("LED","TYO").isNotEmpty())
    }

    @Test
    fun getPricesFail() {
        aviasalesApi.getPrices("LED","GGG").isNotEmpty()
    }
}