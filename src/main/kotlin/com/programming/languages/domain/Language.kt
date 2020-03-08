package com.programming.languages.domain

data class Language(
        val id: Long? = null,
        val name: String,
        val designed: String?,
        val year: Int?,
        val version: String?,
        val web: String?
)
