package com.programming.languages.repository.entity

import com.programming.languages.domain.Language
import javax.persistence.*

@Entity
@Table(name = "language")
data class LanguageEntity(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?,
        val name: String?,
        val designed: String,
        val year: Int,
        val version: String?
)

fun Language.toEntity(): LanguageEntity = LanguageEntity(id, name, designed, year, version)
fun LanguageEntity.toDomain(): Language = Language(
        id, name, designed, year, version, total = null, stars = null, forks = null, watchers = null, openIssues = null
)