package com.programming.languages.rest

import com.programming.languages.rest.dto.LanguageRequest
import com.programming.languages.rest.dto.LanguageResponse
import com.programming.languages.rest.dto.toDomain
import com.programming.languages.rest.dto.toDto
import com.programming.languages.usecase.CreateLanguage
import com.programming.languages.usecase.GetLanguage
import com.programming.languages.usecase.RemoveLanguage
import com.programming.languages.usecase.UpdateLanguage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/language")
class LanguageController(
        private val createLanguage: CreateLanguage,
        private val getLanguage: GetLanguage,
        private val updateLanguage: UpdateLanguage,
        private val removeLanguage: RemoveLanguage
) {

    @PostMapping
    fun createLanguage(@RequestBody languageRequest: LanguageRequest): ResponseEntity<LanguageResponse> {
        val languageResponse = createLanguage(languageRequest.toDomain()).toDto()
        return ResponseEntity(languageResponse, HttpStatus.CREATED)
    }

    @GetMapping("/id/{languageId}")
    fun getLanguageById(@PathVariable languageId: Long): LanguageResponse {
        return getLanguage.getById(languageId).toDto()
    }

    @GetMapping("/name/{name}")
    fun getLanguageByName(@PathVariable name: String): LanguageResponse {
        return getLanguage.getByName(name).toDto()
    }

    @GetMapping
    fun getLanguages(): List<LanguageResponse> {
        return getLanguage.getAll().map { it.toDto() }
    }

    @PutMapping("/{languageId}")
    fun updateLanguage(@RequestBody languageRequest: LanguageRequest, @PathVariable languageId: Long): ResponseEntity<LanguageResponse> {
        val languageResponse = updateLanguage(languageRequest.toDomain(), languageId).toDto()
        return ResponseEntity(languageResponse, HttpStatus.CREATED)
    }

    @DeleteMapping("/{languageId}")
    fun deleteLanguage(@PathVariable languageId: Long): ResponseEntity<Unit> {
        removeLanguage(languageId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}