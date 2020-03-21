package com.programming.languages.usecase

import com.programming.languages.given.GivenLanguage
import com.programming.languages.repository.LanguageDao
import com.programming.languages.usecase.exception.NotFoundException
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
internal class GetLanguageTest : GivenLanguage {

    @InjectMockKs
    private lateinit var useCase: GetLanguage
    @MockK
    private lateinit var dao: LanguageDao

    @Test
    fun `should get a language found by id`() {
        val language = LANGUAGE
        every { dao.getById(any()) } returns language

        val result = useCase.getById(1L)

        assertThat(result, Matchers.`is`(Matchers.not(Matchers.nullValue())))
        assertThat(result.id, Matchers.`is`(language.id))
        assertThat(result.name, Matchers.`is`(language.name))
        assertThat(result.designed, Matchers.`is`(language.designed))
        assertThat(result.year, Matchers.`is`(language.year))
        assertThat(result.version, Matchers.`is`(language.version))
        assertThat(result.web, Matchers.`is`(language.web))
    }

    @Test
    fun `should throw NotFoundException when language not found by id`() {
        every { dao.getById(any()) } returns null

        assertThrows<NotFoundException> { useCase.getById(1L) }
    }

    @Test
    fun `should get all languages`() {
        val language = LANGUAGE
        val languages = listOf(language)
        every { dao.getAll() } returns languages

        val result = useCase.getAll()

        assertThat(result, Matchers.`is`(Matchers.not(Matchers.nullValue())))
        assertThat(result[0].id, Matchers.`is`(languages[0].id))
        assertThat(result[0].name, Matchers.`is`(languages[0].name))
        assertThat(result[0].designed, Matchers.`is`(languages[0].designed))
        assertThat(result[0].year, Matchers.`is`(languages[0].year))
        assertThat(result[0].version, Matchers.`is`(languages[0].version))
        assertThat(result[0].web, Matchers.`is`(languages[0].web))
    }

    @Test
    fun `should get a language found by name`() {
        val language = LANGUAGE
        val languageData = LANGUAGE_DATA
        every { dao.getByName(any()) } returns language
        every { dao.getByNameFromGithub(any()) } returns languageData

        val result = useCase.getByName("name")

        assertThat(result, Matchers.`is`(Matchers.not(Matchers.nullValue())))
        assertThat(result.id, Matchers.`is`(language.id))
        assertThat(result.name, Matchers.`is`(language.name))
        assertThat(result.designed, Matchers.`is`(language.designed))
        assertThat(result.year, Matchers.`is`(language.year))
        assertThat(result.version, Matchers.`is`(language.version))
        assertThat(result.web, Matchers.`is`(language.web))
        assertThat(result.total, Matchers.`is`(language.total))
        assertThat(result.stars, Matchers.`is`(language.stars))
        assertThat(result.forks, Matchers.`is`(language.forks))
        assertThat(result.watchers, Matchers.`is`(language.watchers))
        assertThat(result.openIssues, Matchers.`is`(language.openIssues))
    }

    @Test
    fun `should throw NotFoundException when language not found by name`() {
        every { dao.getByName(any()) } returns null
        every { dao.getByNameFromGithub(any()) } returns null

        assertThrows<NotFoundException> { useCase.getByName("name") }
    }


}