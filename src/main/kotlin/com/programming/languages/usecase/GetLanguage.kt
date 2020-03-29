package com.programming.languages.usecase

import com.programming.languages.domain.Language
import com.programming.languages.repository.LanguageDao
import com.programming.languages.repository.http.dto.toDomain
import com.programming.languages.repository.http.dto.toEntity
import com.programming.languages.usecase.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class GetLanguage(private val languageDao: LanguageDao) {

    fun getById(id: Long): Language {
        val language = languageDao.getById(id) ?: throw NotFoundException("No language in DB for id: $id")
        return completeLanguageData(language)
    }

    fun getByName(name: String): Language {
        val language = languageDao.getByName(name) ?: throw NotFoundException("No language in DB for name: $name")
        return completeLanguageData(language)
    }

    fun getAll(): List<Language> {
        val languages = mutableListOf<Language>()
        val databaseLanguages = languageDao.getAll() ?: throw NotFoundException("No languages in DB")
        for (l in databaseLanguages) {
            val language = completeLanguageData(l)
            languages.add(language)
        }
        return languages
    }

    private fun completeLanguageData(language: Language): Language {
        val languageGithub = languageDao.getByNameFromGithub(language.name)
                ?: throw NotFoundException("No language in Github for name: ${language.name}")
        val languageHttpEntity = languageGithub.toEntity()
        return languageHttpEntity.toDomain(language)
    }

}