package com.terra.gohere.controller

import com.terra.gohere.dto.ContentLinksDto
import com.terra.gohere.dto.UserContentDto
import com.terra.gohere.model.UserContent
import com.terra.gohere.service.UserContentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/content")
class UserContentController(
        @Autowired val userContentService: UserContentService
) {

    @GetMapping("/generateContentLinks")
    fun generateUploadLink(): ContentLinksDto {
        return userContentService.generateContentLinks()
    }

    @GetMapping
    fun content(): List<UserContent> {
        return userContentService.getAll()
    }

    @PostMapping
    fun save(@RequestBody userContentDto: UserContentDto) {
        userContentService.save(userContentDto)
    }
}