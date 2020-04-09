package com.programming.languages.security

import com.programming.languages.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUser(
  val id: String,
  private val username: String,
  private val password: String,
  private val authorities: List<GrantedAuthority> = emptyList()
) : UserDetails {
  override fun getAuthorities(): Collection<GrantedAuthority> = authorities

  override fun isEnabled(): Boolean = true

  override fun getUsername(): String = username

  override fun isCredentialsNonExpired(): Boolean = true

  override fun getPassword(): String = password

  override fun isAccountNonExpired(): Boolean = true

  override fun isAccountNonLocked(): Boolean = true
}

fun User.toSecurityUser(): SecurityUser = SecurityUser(
  id.toString(), username, password, getRole()
)

private fun User.getRole() = listOf(
    GrantedAuthority { "ROLE_USER" }
)