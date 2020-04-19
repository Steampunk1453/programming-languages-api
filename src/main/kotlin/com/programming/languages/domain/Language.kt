package com.programming.languages.domain

data class Language(
        var id: Long? = null,
        val name: String?,
        val designed: String,
        val year: Int,
        val version: String?,
        val total: Int?,
        val stars: Int?,
        val forks: Int?,
        val watchers: Int?,
        val openIssues: Int?
)
