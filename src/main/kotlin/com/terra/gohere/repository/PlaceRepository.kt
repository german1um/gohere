package com.terra.gohere.repository

import com.terra.gohere.model.Place
import org.springframework.data.mongodb.repository.MongoRepository


interface PlaceRepository : MongoRepository<Place, String> {
}