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
    fun createLanguage(@RequestBody languageRequest: LanguageRequest): ResponseEntity<Unit> {
        createLanguage(languageRequest.toDomain())
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/language/id/{languageId}")
    fun getLanguageById(@PathVariable languageId: Long): LanguageResponse {
        return getLanguage.getById(languageId).toDto()
    }

    @GetMapping("/language/name/{name}")
    fun getLanguageByName(@PathVariable name: String): LanguageResponse {
        return getLanguage.getByName(name).toDto()
    }

    @GetMapping("/language")
    fun getLanguages(): List<LanguageResponse> {
        return getLanguage.getAll().map { it.toDto() }
    }

    @PutMapping("/language{languageId}")
    fun updateLanguage(@RequestBody languageRequest: LanguageRequest, @PathVariable languageId: Long): ResponseEntity<Unit> {
        updateLanguage(languageRequest.toDomain(), languageId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/language/{languageId}")
    fun deleteLanguage(@PathVariable languageId: Long): ResponseEntity<Unit> {
        removeLanguage(languageId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}