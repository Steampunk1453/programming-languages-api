package com.programming.languages.repository

import com.programming.languages.domain.Language
import javax.persistence.*

@Entity
@Table(name = "language")
data class LanguageEntity(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?,
        val name: String?,
        val version: String?,
        val year: Int,
        val designed: String,
        val web: String
)

fun Language.toEntity(): LanguageEntity = LanguageEntity(id, name, version, year, designed, web)

fun LanguageEntity.toDomain(): Language = Language(
        id, name, version, year, designed, web, total = null, stars = null, forks = null, watchers = null, openIssues = null
)