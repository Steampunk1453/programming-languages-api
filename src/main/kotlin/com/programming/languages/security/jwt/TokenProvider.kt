package com.programming.languages.security.jwt

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import mu.KotlinLogging

import org.springframework.stereotype.Component
import java.util.Date
import java.util.concurrent.TimeUnit

@Component
class TokenProvider {

  companion object {
    private const val SECRET_TOKEN = "*secret*key"
    private val SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512
    private val EXPIRES_IN_TEN_MINUTES = TimeUnit.MINUTES.toMillis(10)
  }

  private val logger = KotlinLogging.logger {}

  fun generateToken(username: String): String =
    Jwts.builder()
      .setSubject(username)
      .setIssuedAt(now())
      .setExpiration(getExpirationDate())
      .signWith(SIGNATURE_ALGORITHM, SECRET_TOKEN)
      .compact()

  fun isValidToken(token: String): Boolean {
    try {
      Jwts.parser().setSigningKey(SECRET_TOKEN).parseClaimsJws(token)
      return true
    } catch (e: JwtException) {
      logger.info("Invalid JWT token", e)
    } catch (e: IllegalArgumentException) {
      logger.info("Invalid JWT token", e)
    }
    return false
  }

  fun getUsernameFromToken(token: String): String =
    Jwts.parser()
      .setSigningKey(SECRET_TOKEN)
      .parseClaimsJws(token)
      .body
      .subject

  private fun now() = Date(System.currentTimeMillis())
  private fun getExpirationDate() = Date(now().time + EXPIRES_IN_TEN_MINUTES)
}