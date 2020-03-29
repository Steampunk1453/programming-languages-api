package com.programming.languages.rest.dto

import com.programming.languages.domain.User
import org.springframework.security.crypto.password.PasswordEncoder

data class UserRequest(
        val username: String,
        val password: String,
        val email: String
)

fun UserRequest.toDomain(passwordEncoder: PasswordEncoder): User = User(
        username = username,
        password = passwordEncoder.encode(password),
        email = email
)

