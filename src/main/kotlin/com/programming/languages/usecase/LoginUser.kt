package com.programming.languages.usecase

import com.programming.languages.config.SetAuthentication
import com.programming.languages.security.jwt.TokenProvider
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component

@Component
class LoginUser(
  private val authenticationManager: AuthenticationManager,
  private val tokenProvider: TokenProvider,
  private val setAuthentication: SetAuthentication
) {

  operator fun invoke(userPass: UsernamePasswordAuthenticationToken): String {
    val authentication = authenticationManager.authenticate(userPass)
    setAuthentication(authentication)
    return tokenProvider.generateToken(userPass.principal.toString())
  }

}