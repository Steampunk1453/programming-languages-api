package com.programming.languages.usecase

import com.programming.languages.domain.User
import com.programming.languages.repository.UserDao
import com.programming.languages.usecase.exception.EmailAlreadyUsedException
import com.programming.languages.usecase.exception.LoginAlreadyUsedException
import org.springframework.stereotype.Component

@Component
class CreateUser(private val userDao: UserDao) {

    operator fun invoke(user: User): User {
        if (userDao.getByUsername(user.username.toLowerCase()).isPresent) {
            throw LoginAlreadyUsedException("User ${user.username} already in use")
        } else if (userDao.getByEmail(user.email.toLowerCase()).isPresent) {
            throw EmailAlreadyUsedException("Email ${user.email} already in use")
        }
        return userDao.create(user)
    }

}