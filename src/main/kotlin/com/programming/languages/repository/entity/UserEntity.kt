package com.programming.languages.repository.entity

import com.programming.languages.domain.User
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
data class UserEntity(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?,
        val username: String,
        val password: String,
        val email: String
)

fun User.toEntity(): UserEntity = UserEntity(id, username, password, email)
fun Optional<UserEntity>.toDomainOptional(): Optional<User> = this.map { it.toDomain() }
fun UserEntity.toDomain(): User = User(id, username, password, email)

