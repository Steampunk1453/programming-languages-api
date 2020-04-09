package com.programming.languages.domain

data class User(
        var id: Long? = null,
        val username: String,
        val password: String,
        val email: String
)