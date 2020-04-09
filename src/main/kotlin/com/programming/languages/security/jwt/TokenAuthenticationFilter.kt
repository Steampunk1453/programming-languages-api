package com.programming.languages.security.jwt

import com.programming.languages.config.SetAuthentication
import com.programming.languages.security.DomainUserDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenAuthenticationFilter(
        private val tokenProvider: TokenProvider,
        private val domainUserDetailsService: DomainUserDetailsService,
        private val setAuthentication: SetAuthentication
) : OncePerRequestFilter() {

  companion object {
    private const val AUTHORIZATION_HEADER = "Authorization"
    private const val BEARER = "Bearer "
  }

  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    filterChain: FilterChain
  ) {
      val token = getJwtToken(request)

      if (token != null && tokenProvider.isValidToken(token)) {
        val username = tokenProvider.getUsernameFromToken(token)
        val userDetails = domainUserDetailsService.loadUserByUsername(username)
        val authentication =
          UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        setAuthentication(authentication)
      }

    filterChain.doFilter(request, response)
  }

  private fun getJwtToken(request: HttpServletRequest): String? =
    request.getHeader(AUTHORIZATION_HEADER)?.let { token ->
      if (token.startsWith(BEARER)) {
        token.substring(7)
      } else {
        null
      }
    }
}