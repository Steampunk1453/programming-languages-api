package com.programming.languages.usecase

import com.programming.languages.given.GivenLanguage
import com.programming.languages.repository.UserDao
import com.programming.languages.usecase.exception.EmailAlreadyUsedException
import com.programming.languages.usecase.exception.LoginAlreadyUsedException
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
internal class CreateUserTest : GivenLanguage {

    @InjectMockKs
    private lateinit var useCase: CreateUser
    @MockK
    private lateinit var dao: UserDao

    @Test
    fun `should create a user`() {
        val user = USER
        val userDatabase = USER
        every { dao.getByUsername(any()) } returns  Optional.ofNullable(null)
        every { dao.getByEmail(any()) } returns Optional.ofNullable(null)
        every { dao.create(any()) } returns userDatabase

        val result = useCase.invoke(user)

        MatcherAssert.assertThat(result, Matchers.`is`(Matchers.not(Matchers.nullValue())))
        MatcherAssert.assertThat(result.username, Matchers.`is`(user.username))
        MatcherAssert.assertThat(result.password, Matchers.`is`(user.password))
        MatcherAssert.assertThat(result.email, Matchers.`is`(user.email))
    }

    @Test
    fun `should throw LoginAlreadyUsedException when create a user`() {
        val user = USER
        val userDatabase = USER
        every { dao.getByUsername(any()) } returns Optional.ofNullable(userDatabase)
        every { dao.getByEmail(any()) } returns Optional.ofNullable(null)
        every { dao.create(any()) } returns userDatabase

        assertThrows<LoginAlreadyUsedException> { useCase.invoke(user) }
    }

    @Test
    fun `should throw EmailAlreadyUsedException when create a user`() {
        val user = USER
        val userDatabase = USER
        every { dao.getByUsername(any()) } returns Optional.ofNullable(null)
        every { dao.getByEmail(any()) } returns Optional.ofNullable(userDatabase)
        every { dao.create(any()) } returns userDatabase

        assertThrows<EmailAlreadyUsedException> { useCase.invoke(user) }
    }

}