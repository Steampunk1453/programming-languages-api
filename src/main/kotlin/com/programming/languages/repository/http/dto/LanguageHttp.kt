package com.programming.languages.repository.http.dto

import com.programming.languages.domain.Language

data class LanguageHttpEntity(
        val total: Int,
        val stars: Int,
        val forks: Int,
        val watchers: Int,
        val openIssues: Int
)

fun LanguageHttpEntity.toDomain(language: Language): Language {
    return language.copy(total = total, stars = stars, forks = forks,
            watchers = watchers, openIssues = openIssues)
}

