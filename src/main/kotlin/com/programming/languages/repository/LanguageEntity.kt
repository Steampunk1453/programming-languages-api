package com.programming.languages.repository

import com.programming.languages.domain.Language
import javax.persistence.*

@Entity
data class LanguageEntity(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val name: String,
        val designed: String?,
        val year: Int?,
        val version: String?,
        val web: String?
)

fun Language.toEntity(): LanguageEntity = LanguageEntity(id, name, designed, year, version, web)

fun LanguageEntity.toDomain(): Language = Language(
        id, name, designed, year, version, web
)