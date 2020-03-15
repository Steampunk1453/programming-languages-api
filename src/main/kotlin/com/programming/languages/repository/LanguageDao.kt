package com.programming.languages.repository

import com.programming.languages.domain.Language
import org.springframework.stereotype.Component

@Component
class LanguageDao(private val languageRepository: LanguageRepository) {

    fun save(language: Language): Language {
        return languageRepository.save(language.toEntity()).toDomain()
    }

    fun getById(id: Long): Language? {
        return languageRepository.findById(id).orElse(null)?.toDomain()
    }

    fun getAll(): List<Language>? {
       return languageRepository.findAll().map { it.toDomain() }
    }

    fun delete(id: Long) {
        languageRepository.deleteById(id)
    }

}