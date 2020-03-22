package com.programming.languages.rest

import com.programming.languages.domain.Language
import com.programming.languages.given.GivenLanguage
import com.programming.languages.repository.LanguageDao
import com.programming.languages.usecase.exception.NotFoundException
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LanguageControllerIT(
        @Autowired  private val restTemplate: TestRestTemplate,
        @Autowired  private val dao: LanguageDao
) : GivenLanguage{

    @Test
    @DirtiesContext
    fun `POST should create a language`() {
        val language = LANGUAGE

        val response = restTemplate.postForEntity(
                "/language",
                language.toDto(),
                String::class.java,
                emptyMap<String, String>()
        )

        response.statusCode shouldBe HttpStatus.NO_CONTENT
    }

    @Test
    @DirtiesContext
    fun `POST should returns 409 if the language is in DB`() {
        val language = LANGUAGE
        create(language)

        val response = restTemplate.postForEntity(
                "/language",
                language.toDto(),
                String::class.java,
                emptyMap<String, String>()
        )

        response.statusCode shouldBe HttpStatus.CONFLICT
    }

    @Test
    @DirtiesContext
    fun `POST should returns 415 if the json body isn't expected`() {
        val entity = HttpEntity("")

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
        val languageId = LANGUAGE.id
        val language = LANGUAGE
        create(language)

        val response = restTemplate.getForEntity(
                "/language/id/$languageId",
                LanguageResponse::class.java
        )

        response.statusCode shouldBe HttpStatus.OK
        response.body?.designed shouldBe language.designed
        response.body?.name shouldBe language.name
        response.body?.version shouldBe language.version
        response.body?.web shouldBe language.web
        response.body?.year shouldBe language.year
    }

    @Test
    @DirtiesContext
    fun `GET should returns 404 if there isn't language search by id`() {
        create(LANGUAGE)

        val response = restTemplate.getForEntity(
                "/language/id/$LANGUAGE_ID",
                NotFoundException::class.java
        )

        response.statusCode shouldBe HttpStatus.NOT_FOUND
    }

    @Test
    @DirtiesContext
    fun `GET should get a language by name`() {
        val languageName = LANGUAGE.name
        val language = LANGUAGE
        create(language)

        val response = restTemplate.getForEntity(
                "/language/name/$languageName",
                LanguageResponse::class.java
        )

        response.statusCode shouldBe HttpStatus.OK
        response.body?.designed shouldBe language.designed
        response.body?.name shouldBe language.name
        response.body?.version shouldBe language.version
        response.body?.web shouldBe language.web
        response.body?.year shouldBe language.year
        response.body?.total shouldNotBe null
        response.body?.stars shouldNotBe null
        response.body?.forks shouldNotBe null
        response.body?.watchers shouldNotBe null
        response.body?.openIssues shouldNotBe null
    }

    @Test
    @DirtiesContext
    fun `GET should returns 404 if there isn't language search by name`() {
        create(LANGUAGE)

        val response = restTemplate.getForEntity(
                "/language/name/$LANGUAGE_NAME",
                NotFoundException::class.java
        )

        response.statusCode shouldBe HttpStatus.NOT_FOUND
    }

    @Test
    @DirtiesContext
    fun `GET should get all languages`() {
        val language = LANGUAGE
        create(language)

        val response = restTemplate.getForEntity(
                "/language/",
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
        val language = LANGUAGE
        val languageId = LANGUAGE.id
        val entity = HttpEntity(language.toDto())
        val param = mutableMapOf<String, String>()
        param["languageId"] = languageId.toString()
        create(language)

        val response = restTemplate.exchange(
                "/language{languageId}",
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
        val languageId = LANGUAGE.id
        create(LANGUAGE)

        val response = restTemplate.delete(
                "/language/$languageId"
        )

        response shouldNotBe(null)
    }

    private class LanguageList : MutableList<Language> by ArrayList()

    private fun create(language: Language) = dao.save(language)

}

