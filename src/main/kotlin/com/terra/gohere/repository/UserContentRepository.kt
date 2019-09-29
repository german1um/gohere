package com.terra.gohere.repository

import com.terra.gohere.model.Place
import com.terra.gohere.model.UserContent
import org.springframework.data.mongodb.repository.MongoRepository

interface UserContentRepository: MongoRepository<UserContent, String> {
}