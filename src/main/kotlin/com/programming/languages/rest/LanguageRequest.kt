package com.programming.languages.rest

import com.programming.languages.domain.Language

data class LanguageRequest(
        val name: String?,
        val version: String?,
        val year: Int,
        val designed: String,
        val web: String
)

fun LanguageRequest.toDomain(): Language = Language(name = name, version = version, year = year, designed = designed,
        web = web, total = null, stars = null, forks = null, watchers = null, openIssues = null
)