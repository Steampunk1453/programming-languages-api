package com.programming.languages.rest.dto

import com.programming.languages.domain.Language

data class LanguageRequest(
        val name: String?,
        val version: String?,
        val year: Int,
        val designed: String
)

fun LanguageRequest.toDomain(): Language = Language(name = name, designed = designed, year = year, version = version,
       total = null, stars = null, forks = null, watchers = null, openIssues = null
)