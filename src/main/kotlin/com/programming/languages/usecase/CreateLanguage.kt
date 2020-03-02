package com.programming.languages.usecase

import com.programming.languages.domain.Language
import com.programming.languages.repository.LanguageDao
import com.programming.languages.repository.LanguageRepository
import com.programming.languages.repository.toDomain
import com.programming.languages.repository.toEntity
import org.springframework.stereotype.Service

@Service
class CreateLanguage(private val languageDao: LanguageDao) {

    operator fun invoke(language: Language): Language {
       return languageDao.create(language)
    }

}