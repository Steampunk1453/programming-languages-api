package com.programming.languages.repository

import com.programming.languages.domain.User
import com.programming.languages.repository.entity.UserEntity
import com.programming.languages.repository.entity.toDomain
import com.programming.languages.repository.entity.toDomainOptional
import com.programming.languages.repository.entity.toEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): Optional<UserEntity>
    fun findByUsername(username: String): Optional<UserEntity>
}

@Component
class UserDao(private val userRepository: UserRepository) {
    fun create(user: User): User = userRepository.save(user.toEntity()).toDomain()
    fun getByEmail(email: String): Optional<User> = userRepository.findByEmail(email).toDomainOptional()
    fun getByUsername(username: String): Optional<User> = userRepository.findByUsername(username).toDomainOptional()
}