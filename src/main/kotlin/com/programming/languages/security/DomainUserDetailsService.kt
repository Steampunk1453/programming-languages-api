package com.programming.languages.security

import com.programming.languages.domain.User
import com.programming.languages.repository.UserDao
import mu.KotlinLogging
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.stream.Collectors


@Service
class DomainUserDetailsService(private val userDao: UserDao) : UserDetailsService {

    private val logger = KotlinLogging.logger {}

    @Transactional
    override fun loadUserByUsername(login: String): UserDetails {
        logger.debug("Authenticating {}", login)

        if (EmailValidator().isValid(login, null)) {
            return userDao.getByEmail(login)
                    .map { it.toSecurityUser() }
                    .orElseThrow { UsernameNotFoundException("User with email $login was not found in the database") }
        }

        val lowercaseLogin = login.toLowerCase()
        return userDao.getByUsername(lowercaseLogin)
                .map { it.toSecurityUser() }
                .orElseThrow { UsernameNotFoundException("User $lowercaseLogin was not found in the database") }
        }

    }