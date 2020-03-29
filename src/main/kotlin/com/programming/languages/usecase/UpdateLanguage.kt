package com.programming.languages.usecase

import com.programming.languages.domain.Language
import com.programming.languages.repository.LanguageDao
import com.programming.languages.usecase.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class UpdateLanguage(private val languageDao: LanguageDao) {

    operator fun invoke(language: Language, id: Long): Language {
        languageDao.getById(id)?: throw NotFoundException("No language for id: $id")
        language.id = id
        return languageDao.create(language)
    }

}
