package com.programming.languages.rest

import com.programming.languages.rest.dto.*
import com.programming.languages.usecase.CreateUser
import com.programming.languages.usecase.LoginUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
        private val createUser: CreateUser,
        private val loginUser: LoginUser,
        private val passwordEncoder: PasswordEncoder
) {

  @PostMapping("/register")
  fun registerUser(@RequestBody request: UserRequest): ResponseEntity<UserResponse> {
    val userResponse = createUser(request.toDomain(passwordEncoder)).toDto()
    return ResponseEntity(userResponse,HttpStatus.CREATED)
  }

  @PostMapping("/login")
  fun loginUser(@RequestBody request: LoginRequest): String {
    val userPass = UsernamePasswordAuthenticationToken(request.username, request.password)
    return loginUser(userPass)
  }
}