package com.programming.languages.usecase

import com.programming.languages.domain.Language
import com.programming.languages.repository.LanguageDao
import org.springframework.stereotype.Service

@Service
class CreateLanguage(private val languageDao: LanguageDao) {

    operator fun invoke(language: Language): Language {
       return languageDao.save(language)
    }

}