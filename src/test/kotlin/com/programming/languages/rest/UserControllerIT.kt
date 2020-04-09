package com.programming.languages.rest

import com.programming.languages.domain.User
import com.programming.languages.given.GivenLanguage
import com.programming.languages.repository.UserDao
import com.programming.languages.repository.UserRepository
import com.programming.languages.repository.entity.toEntity
import com.programming.languages.rest.dto.UserRequest
import com.programming.languages.rest.dto.UserResponse
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIT(@Autowired private val restTemplate: TestRestTemplate,
                       @Autowired private val dao: UserDao,
                       @Autowired private val repository: UserRepository
) : GivenLanguage {

    @Test
    @DirtiesContext
    fun `POST should register a User`() {
        val userRequest = USER_REQUEST

        val response = restTemplate.postForEntity(
                "/user/register",
                userRequest,
                UserResponse::class.java,
                emptyMap<String, String>()
        )

        response.statusCode shouldBe HttpStatus.CREATED
        response.body?.id shouldBe "1"
        response.body?.username shouldBe userRequest.username
        response.body?.email shouldBe userRequest.email
    }

    @Test
    @DirtiesContext
    fun `POST should returns 409 if the username is in DB`() {
        val user = USER
        val userRequest = USER_REQUEST
        create(user)

        val response = restTemplate.postForEntity(
                "/user/register",
                userRequest,
                UserResponse::class.java,
                emptyMap<String, String>()
        )

        response.statusCode shouldBe HttpStatus.CONFLICT
    }

    @Test
    @DirtiesContext
    fun `POST should returns 409 if the email is in DB`() {
        val user = USER
        val userRequest = NEW_USER_REQUEST
        create(user)

        val response = registerUser(userRequest)

        response?.statusCode shouldBe HttpStatus.CONFLICT
    }

    @Test
    @DirtiesContext
    fun `POST should login a User`() {
        val userRequest = USER_REQUEST
        val loginRequest = LOGIN_REQUEST
        registerUser(userRequest)

        val response = restTemplate.postForEntity(
                "/user/login",
                loginRequest,
                String::class.java,
                emptyMap<String, String>()
        )

        response.statusCode shouldBe HttpStatus.OK
        response.body shouldNotBe null
    }

    private fun create(user: User) = repository.save(user.toEntity())

    private fun registerUser(userRequest: UserRequest): ResponseEntity<UserResponse>? {
        return restTemplate.postForEntity(
                "/user/register",
                userRequest,
                UserResponse::class.java,
                emptyMap<String, String>()
        )
    }

}