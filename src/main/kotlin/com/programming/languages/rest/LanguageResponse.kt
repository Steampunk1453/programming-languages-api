package com.programming.languages.rest

import com.programming.languages.domain.Language

data class LanguageResponse(
        val id: Long?,
        val name: String?,
        val version: String?,
        val year: Int,
        val designed: String,
        val web: String,
        val total: Int?,
        val stars: Int?,
        val forks: Int?,
        val watchers: Int?,
        val openIssues: Int?
)

fun Language.toDto(): LanguageResponse = LanguageResponse(id, name, version, year, designed, web, total, stars, forks, watchers, openIssues)

