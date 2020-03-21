package com.programming.languages.repository

import com.programming.languages.domain.Language
import com.programming.languages.repository.http.ApiLanguageRepository
import com.programming.languages.repository.http.LanguageGithub
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class LanguageDao(private val languageRepository: LanguageRepository,
                  private val githubLanguageRepository: ApiLanguageRepository
) {

    @Value("\${github.query}")
    private lateinit var githubQuery: String

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

    fun getByName(name: String): Language? {
        return languageRepository.findByName(name).orElse(null)?.toDomain()
    }

    fun getByNameFromGithub(name: String): LanguageGithub? {
        val query = githubQuery + name
        return githubLanguageRepository.getLanguageData(query)
    }

}