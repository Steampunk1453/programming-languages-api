package com.programming.languages.rest

import com.programming.languages.domain.Language

data class LanguageDto(
        val name: String,
        val designed: String?,
        val year: Int?,
        val version: String?,
        val web: String?
)

fun Language.toDto(): LanguageDto = LanguageDto(name, designed, year, version, web)

fun LanguageDto.toDomain(): Language = Language(
        name = name,
        designed = designed,
        year = year,
        version = version,
        web = web
)