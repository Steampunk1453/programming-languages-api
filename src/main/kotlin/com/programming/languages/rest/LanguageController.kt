package com.programming.languages.rest

import com.programming.languages.usecase.CreateLanguage
import com.programming.languages.usecase.GetLanguage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class LanguageController(
        private val createLanguage: CreateLanguage,
        private val getLanguage: GetLanguage
) {
    @PostMapping("/language")
    fun createLanguage(@RequestBody languageDto: LanguageDto): ResponseEntity<Unit> {
        createLanguage(languageDto.toDomain())
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping("/language/{languageId}")
    fun getLanguage(@PathVariable languageId: Long): LanguageDto {
        return getLanguage.getById(languageId).toDto()
    }

}