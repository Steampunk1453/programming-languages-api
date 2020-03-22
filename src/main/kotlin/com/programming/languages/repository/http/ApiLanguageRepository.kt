package com.programming.languages.repository.http

interface ApiLanguageRepository {
    fun getLanguageData(query: String): LanguageGithub?
}