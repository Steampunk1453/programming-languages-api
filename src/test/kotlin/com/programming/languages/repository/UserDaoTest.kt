package com.programming.languages.repository

import com.programming.languages.domain.User
import com.programming.languages.given.GivenLanguage
import com.programming.languages.repository.entity.toEntity
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@Transactional
class UserDaoTest(
        @Autowired private val repository: UserRepository
) : GivenLanguage {

    @Test
    @DirtiesContext
    fun `should create user`() {
        val user = USER

        val result = create(user)

        result shouldNotBe {null}
        result.id shouldBe user.id
        result.username shouldBe user.username
        result.password shouldBe user.password
        result.email shouldBe user.email
    }

    @Test
    @DirtiesContext
    fun `should get user by email`() {
        val user = USER
        create(user)

        val result = repository.findByEmail(user.email)

        result shouldNotBe {null}
        result.get().id shouldBe user.id
        result.get().username shouldBe user.username
        result.get().password shouldBe user.password
        result.get().email shouldBe user.email
    }

    @Test
    @DirtiesContext
    fun `should get user by username`() {
        val user = USER
        create(user)

        val result = repository.findByUsername(user.username)

        result shouldNotBe {null}
        result.get().id shouldBe user.id
        result.get().username shouldBe user.username
        result.get().password shouldBe user.password
        result.get().email shouldBe user.email
    }

    private fun create(user: User) = repository.save(user.toEntity())

}