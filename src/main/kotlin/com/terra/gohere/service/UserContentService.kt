package com.terra.gohere.service

import com.terra.gohere.cdn.GcsResolver
import com.terra.gohere.dto.ContentLinksDto
import com.terra.gohere.dto.UserContentDto
import com.terra.gohere.model.UserContent
import com.terra.gohere.repository.UserContentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserContentService(
        @Autowired val gcsResolver: GcsResolver,
        @Autowired val userContentRepository: UserContentRepository
) {

    fun generateContentLinks(): ContentLinksDto {
        val filename = "${UUID.randomUUID()}"
        return gcsResolver.generateContentLinks(filename)
    }

    fun getAll(): List<UserContent> {
        return userContentRepository.findAll()
    }

    fun save(userContentDto: UserContentDto) {
        userContentRepository.save(
                UserContent(
                        userId = userContentDto.userId,
                        contentLink = userContentDto.contentLink
                )
        )
    }


}