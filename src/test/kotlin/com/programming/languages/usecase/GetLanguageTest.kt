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
        assertThat(result?.id, Matchers.`is`(language.id))
        assertThat(result?.name, Matchers.`is`(language.name))
        assertThat(result?.designed, Matchers.`is`(language.designed))
        assertThat(result?.year, Matchers.`is`(language.year))
        assertThat(result?.version, Matchers.`is`(language.version))
        assertThat(result?.web, Matchers.`is`(language.web))
    }

    @Test
    fun `should throw NotFoundException when language not found`() {
        every { dao.getById(any()) } returns null
        assertThrows<NotFoundException> { useCase.getById(1L) }
    }

}