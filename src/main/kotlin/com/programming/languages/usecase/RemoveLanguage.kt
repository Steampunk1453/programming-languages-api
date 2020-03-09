package com.programming.languages.usecase

import com.programming.languages.repository.LanguageDao
import com.programming.languages.usecase.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class RemoveLanguage(private val languageDao: LanguageDao) {

    operator fun invoke(id: Long) {
        languageDao.getById(id)?: throw NotFoundException("No language for id: $id")
        languageDao.delete(id)
    }

}
