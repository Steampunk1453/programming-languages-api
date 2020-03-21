package com.programming.languages.usecase

import com.programming.languages.domain.Language
import com.programming.languages.repository.LanguageDao
import com.programming.languages.repository.http.LanguageGithub
import com.programming.languages.repository.http.LanguageHttpEntity
import com.programming.languages.repository.http.toDomain
import com.programming.languages.repository.http.toEntity
import com.programming.languages.usecase.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class GetLanguage(private val languageDao: LanguageDao) {

    fun getById(id: Long): Language {
        return languageDao.getById(id)?: throw NotFoundException("No language for id: $id")
    }

    fun getAll(): List<Language> {
        return languageDao.getAll()?: throw NotFoundException("No languages")
    }

    fun getByName(name: String): Language {
        val language = languageDao.getByName(name)?: throw NotFoundException("No language in DB for name: $name")
        val languageGithub = languageDao.getByNameFromGithub(name)?: throw NotFoundException("No language in Github for name: $name")
        val languageHttpEntity = languageGithub.toEntity()
        return languageHttpEntity.toDomain(language)
    }

}