package com.terra.gohere.service

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
internal class PlaceServiceTest (@Autowired val placeService: PlaceService) {

    @Test
    fun getPrices() {
        assert(placeService.getFlight("LED","TYO","2019-11-01").value > 0)
    }
}