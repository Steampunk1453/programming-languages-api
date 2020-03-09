package com.programming.languages.rest

import com.programming.languages.usecase.CreateLanguage
import com.programming.languages.usecase.RemoveLanguage
import com.programming.languages.usecase.GetLanguage
import com.programming.languages.usecase.UpdateLanguage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class LanguageController(
        private val createLanguage: CreateLanguage,
        private val getLanguage: GetLanguage,
        private val updateLanguage: UpdateLanguage,
        private val removeLanguage: RemoveLanguage
) {

    @PostMapping("/language")
    fun createLanguage(@RequestBody languageDto: LanguageDto): ResponseEntity<Unit> {
        createLanguage(languageDto.toDomain())
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/language/{languageId}")
    fun getLanguage(@PathVariable languageId: Long): LanguageDto {
        return getLanguage.getById(languageId).toDto()
    }

    @GetMapping("/language")
    fun getLanguages(): List<LanguageDto> {
        return getLanguage.getAll().map { it.toDto() }
    }

    @PutMapping("/language{languageId}")
    fun updateLanguage(@RequestBody languageDto: LanguageDto,  @PathVariable languageId: Long): ResponseEntity<Unit> {
        updateLanguage(languageDto.toDomain(), languageId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/language/{languageId}")
    fun deleteLanguage(@PathVariable languageId: Long): ResponseEntity<Unit> {
        removeLanguage(languageId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}