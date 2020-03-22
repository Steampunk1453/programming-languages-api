package com.programming.languages.usecase

import com.programming.languages.domain.Language
import com.programming.languages.repository.LanguageDao
import com.programming.languages.usecase.exception.AlreadyRegisteredException
import org.springframework.stereotype.Service

@Service
class CreateLanguage(private val languageDao: LanguageDao) {

    operator fun invoke(newLanguage: Language): Language? {
        val language = languageDao.getByName(newLanguage.name)
        if (language?.name == newLanguage.name) {
            throw AlreadyRegisteredException("Language already registered: ${newLanguage.name}")
        }
        return languageDao.save(newLanguage)
    }

}