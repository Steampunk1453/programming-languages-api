package com.programming.languages.rest.dto

import com.programming.languages.domain.User

data class UserResponse(
        var id: String,
        val username: String,
        val email: String
)

fun User.toDto(): UserResponse = UserResponse(id.toString(), username, email)

