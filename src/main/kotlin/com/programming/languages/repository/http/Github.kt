package com.programming.languages.repository.http

import com.fasterxml.jackson.annotation.JsonProperty

data class LanguageGithub(
        @JsonProperty("total_count")
        val total: Int,
        @JsonProperty("items")
        val items: List<Item>
)

fun LanguageGithub.toEntity(): LanguageHttpEntity = LanguageHttpEntity(
        total = total,
        stars = items.map { it.stars }.sumBy { it },
        forks = items.map { it.forks }.sumBy { it },
        watchers = items.map { it.watchers }.sumBy { it },
        openIssues = items.map { it.openIssues }.sumBy { it }
)



