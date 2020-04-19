package com.programming.languages.repository

import com.programming.languages.domain.Language
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
class LanguageDaoTest(
        @Autowired private val repository: LanguageRepository
) : GivenLanguage {

    @Test
    @DirtiesContext
    fun `should create language`() {
        val language = LANGUAGE

        val result = create(language)

        result shouldNotBe {null}
        result.id shouldBe language.id
        result.name shouldBe language.name
        result.designed shouldBe language.designed
        result.year shouldBe language.year
        result.version shouldBe language.version
    }

    @Test
    @DirtiesContext
    fun `should get language by id`() {
        val language = LANGUAGE
        val id = 1L
        create(language)

        val result = repository.findById(id)

        result shouldNotBe {null}
        result.get() shouldNotBe {null}
        result.get().id shouldBe language.id
        result.get().name shouldBe language.name
        result.get().designed shouldBe language.designed
        result.get().year shouldBe language.year
        result.get().version shouldBe language.version
    }

    @Test
    @DirtiesContext
    fun `should get language by name`() {
        val language = LANGUAGE
        val name = "Kotlin"
        create(language)

        val result = repository.findByName(name)

        result shouldNotBe {null}
        result.get() shouldNotBe {null}
        result.get().id shouldBe language.id
        result.get().name shouldBe language.name
        result.get().designed shouldBe language.designed
        result.get().year shouldBe language.year
        result.get().version shouldBe language.version
    }

    @Test
    @DirtiesContext
    fun `should get all languages`() {
        val language = LANGUAGE
        create(language)

        val result = repository.findAll()

        result shouldNotBe {null}
        result[0].id shouldBe language.id
        result[0].name shouldBe language.name
        result[0].designed shouldBe language.designed
        result[0].year shouldBe language.year
        result[0].version shouldBe language.version
    }

    @Test
    @DirtiesContext
    fun delete() {
        val language = LANGUAGE
        val id = 1L
        create(language)

        repository.deleteById(id)
    }

    private fun create(language: Language) = repository.save(language.toEntity())
}