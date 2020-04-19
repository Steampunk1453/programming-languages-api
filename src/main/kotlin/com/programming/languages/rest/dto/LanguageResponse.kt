package com.programming.languages.rest.dto

import com.programming.languages.domain.Language

data class LanguageResponse(
        val id: String?,
        val name: String?,
        val designed: String?,
        val year: Int,
        val version: String?,
        val total: Int?,
        val stars: Int?,
        val forks: Int?,
        val watchers: Int?,
        val openIssues: Int?
)

fun Language.toDto(): LanguageResponse = LanguageResponse(id.toString(), name, designed, year, version, total, stars, forks, watchers, openIssues)

