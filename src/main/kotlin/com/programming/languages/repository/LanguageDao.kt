package com.programming.languages.repository

import com.programming.languages.domain.Language
import org.springframework.stereotype.Component

@Component
class LanguageDao(private val languageRepository: LanguageRepository) {

    fun create(language: Language): Language {
        return languageRepository.save(language.toEntity()).toDomain()
    }

    fun getById(id: Long): Language? {
        return languageRepository.findById(id).orElse(null)?.toDomain()
    }

}