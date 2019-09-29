package com.terra.gohere.repository

import com.terra.gohere.model.CachedData
import com.terra.gohere.model.Place
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param


interface CachedDataRepository : MongoRepository<CachedData, String> {
    fun existsByPlaceId(@Param("placeId") placeId: String): Boolean

    fun findByPlaceId(@Param("placeId") placeId: String): CachedData
}