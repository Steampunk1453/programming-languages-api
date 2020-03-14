package com.programming.languages.usecase

import com.programming.languages.domain.Language
import com.programming.languages.repository.LanguageDao
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

}