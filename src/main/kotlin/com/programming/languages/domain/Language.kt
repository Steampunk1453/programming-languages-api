package com.programming.languages.domain

data class Language(
        var id: Long? = null,
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
