package com.programming.languages.rest

import com.programming.languages.domain.Language
import com.programming.languages.domain.User
import com.programming.languages.given.GivenLanguage
import com.programming.languages.repository.LanguageDao
import com.programming.languages.repository.UserRepository
import com.programming.languages.repository.entity.toEntity
import com.programming.languages.rest.dto.LanguageResponse
import com.programming.languages.security.jwt.TokenProvider
import com.programming.languages.usecase.exception.AlreadyRegisteredException
import com.programming.languages.usecase.exception.NotFoundException
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LanguageControllerIT(
        @Autowired  private val restTemplate: TestRestTemplate,
        @Autowired  private val dao: LanguageDao,
        @Autowired private val repository: UserRepository
) : GivenLanguage {

    @Test
    @DirtiesContext
    fun `POST should create a language`() {
        val user = USER
        val languageRequest = LANGUAGE_REQUEST
        val languageResponse = LANGUAGE_RESPONSE
        val headers = HttpHeaders()
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer ${getTokenForTestUser()}")
        val entity = HttpEntity(languageRequest, headers)
        create(user)

        val response = restTemplate.exchange(
                "/language",
                HttpMethod.POST,
                entity,
                LanguageResponse::class.java
        )

        response.statusCode shouldBe HttpStatus.CREATED
        response.body?.designed shouldBe languageResponse.designed
        response.body?.name shouldBe languageResponse.name
        response.body?.version shouldBe languageResponse.version
        response.body?.web shouldBe languageResponse.web
        response.body?.year shouldBe languageResponse.year
    }

    @Test
    @DirtiesContext
    fun `POST should returns 409 if the language is in DB`() {
        val user = USER
        val language = LANGUAGE
        val languageRequest = LANGUAGE_REQUEST
        val headers = HttpHeaders()
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer ${getTokenForTestUser()}")
        val entity = HttpEntity(languageRequest, headers)
        create(user)
        create(language)

        val response = restTemplate.exchange(
                "/language",
                HttpMethod.POST,
                entity,
                AlreadyRegisteredException::class.java
        )

        response.statusCode shouldBe HttpStatus.CONFLICT
    }

    @Test
    @DirtiesContext
    fun `POST should returns 415 if the json body isn't expected`() {
        val user = USER
        val headers = HttpHeaders()
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer ${getTokenForTestUser()}")
        create(user)

        val entity = HttpEntity("", headers)

        val response = restTemplate.exchange(
                "/language",
                HttpMethod.POST,
                entity,
                String::class.java
        )

        response.statusCode shouldBe HttpStatus.UNSUPPORTED_MEDIA_TYPE
    }

    @Test
    @DirtiesContext
    fun `GET should get a language by id`() {
        val user = USER
        val languageId = 2
        val language = LANGUAGE
        val languageResponse = LANGUAGE_RESPONSE
        val headers = HttpHeaders()
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer ${getTokenForTestUser()}")
        val entity = HttpEntity(null, headers)
        create(user)
        create(language)

        val response = restTemplate.exchange(
                "/language/id/$languageId",
                HttpMethod.GET,
                entity,
                LanguageResponse::class.java
        )

        response.statusCode shouldBe HttpStatus.OK
        response.body?.designed shouldBe languageResponse.designed
        response.body?.name shouldBe languageResponse.name
        response.body?.version shouldBe languageResponse.version
        response.body?.web shouldBe languageResponse.web
        response.body?.total shouldNotBe null
        response.body?.stars shouldNotBe null
        response.body?.forks shouldNotBe null
        response.body?.watchers shouldNotBe null
        response.body?.openIssues shouldNotBe null
    }

    @Test
    @DirtiesContext
    fun `GET should returns 404 if there isn't language search by id`() {
        val user = USER
        val language = LANGUAGE
        val languageId = LANGUAGE_ID
        val headers = HttpHeaders()
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer ${getTokenForTestUser()}")
        val entity = HttpEntity(null, headers)
        create(user)
        create(language)

        val response = restTemplate.exchange(
                "/language/id/$languageId",
                HttpMethod.GET,
                entity,
                NotFoundException::class.java
        )

        response.statusCode shouldBe HttpStatus.NOT_FOUND
    }

    @Test
    @DirtiesContext
    fun `GET should get a language by name`() {
        val user = USER
        val languageName = LANGUAGE.name
        val language = LANGUAGE
        val languageResponse = LANGUAGE_RESPONSE
        val headers = HttpHeaders()
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer ${getTokenForTestUser()}")
        val entity = HttpEntity(null, headers)
        create(user)
        create(language)

        val response = restTemplate.exchange(
                "/language/name/$languageName",
                HttpMethod.GET,
                entity,
                LanguageResponse::class.java
        )

        response.statusCode shouldBe HttpStatus.OK
        response.body?.designed shouldBe languageResponse.designed
        response.body?.name shouldBe languageResponse.name
        response.body?.version shouldBe languageResponse.version
        response.body?.web shouldBe languageResponse.web
        response.body?.year shouldBe languageResponse.year
        response.body?.total shouldNotBe null
        response.body?.stars shouldNotBe null
        response.body?.forks shouldNotBe null
        response.body?.watchers shouldNotBe null
        response.body?.openIssues shouldNotBe null
    }

    @Test
    @DirtiesContext
    fun `GET should returns 404 if there isn't language search by name`() {
        val user = USER
        val language = LANGUAGE
        val headers = HttpHeaders()
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer ${getTokenForTestUser()}")
        val entity = HttpEntity(null, headers)
        create(user)
        create(language)

        val response = restTemplate.exchange(
                "/language/name/$LANGUAGE_NAME",
                HttpMethod.GET,
                entity,
                NotFoundException::class.java
        )

        response.statusCode shouldBe HttpStatus.NOT_FOUND
    }

    @Test
    @DirtiesContext
    fun `GET should get all languages`() {
        val user = USER
        val language = LANGUAGE
        val headers = HttpHeaders()
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer ${getTokenForTestUser()}")
        val entity = HttpEntity(null, headers)
        create(user)
        create(language)

        val response = restTemplate.exchange(
                "/language/",
                HttpMethod.GET,
                entity,
                LanguageList::class.java
        )

        response.statusCode shouldBe HttpStatus.OK
        response.body?.get(0)?.designed shouldBe language.designed
        response.body?.get(0)?.name shouldBe language.name
        response.body?.get(0)?.version shouldBe language.version
        response.body?.get(0)?.web shouldBe language.web
        response.body?.get(0)?.year shouldBe language.year
        response.body?.get(0)?.total shouldNotBe null
        response.body?.get(0)?.stars shouldNotBe null
        response.body?.get(0)?.forks shouldNotBe null
        response.body?.get(0)?.watchers shouldNotBe null
        response.body?.get(0)?.openIssues shouldNotBe null
    }

    @Test
    @DirtiesContext
    fun `PUT should update a language by id`() {
        val user = USER
        val language = LANGUAGE
        val languageId = 2
        val newLanguageRequest = NEW_LANGUAGE_REQUEST
        val headers = HttpHeaders()
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer ${getTokenForTestUser()}")
        val entity = HttpEntity(newLanguageRequest, headers)
        val param = mutableMapOf<String, String>()
        param["languageId"] = languageId.toString()
        create(user)
        create(language)

        val response = restTemplate.exchange(
                "/language/{languageId}",
                HttpMethod.PUT,
                entity,
                Void::class.java,
                param
        )

        response.statusCode shouldBe HttpStatus.NO_CONTENT
    }

    @Test
    @DirtiesContext
    fun `DELETE should delete a language by id`() {
        val user = USER
        val language = LANGUAGE
        val languageId = LANGUAGE.id
        val headers = HttpHeaders()
        create(user)
        create(language)
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer ${getTokenForTestUser()}")
        val entity = HttpEntity(null, headers)

        val response = restTemplate.exchange(
                "/language/$languageId",
                HttpMethod.GET,
                entity,
                Void::class.java
        )

        response shouldNotBe(null)
    }

    private class LanguageList : MutableList<Language> by ArrayList()

    private fun create(language: Language) = dao.create(language)
    private fun getTokenForTestUser(): String = TokenProvider().generateToken("username")
    private fun create(user: User) = repository.save(user.toEntity())
}

