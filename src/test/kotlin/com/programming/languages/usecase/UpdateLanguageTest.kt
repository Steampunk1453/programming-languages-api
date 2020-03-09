package com.programming.languages.usecase

import com.programming.languages.given.GivenLanguage
import com.programming.languages.repository.LanguageDao
import com.programming.languages.usecase.exception.NotFoundException
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class UpdateLanguageTest : GivenLanguage {

    @InjectMockKs
    private lateinit var useCase: UpdateLanguage
    @MockK
    private lateinit var dao: LanguageDao

    @Test
    fun `should update a language found by id`() {
        val language = LANGUAGE
        every { dao.getById(any()) } returns language
        every { dao.save(any()) } returns language

        val result = useCase.invoke(LANGUAGE)

        MatcherAssert.assertThat(result, Matchers.`is`(Matchers.not(Matchers.nullValue())))
        MatcherAssert.assertThat(result.id, Matchers.`is`(language.id))
        MatcherAssert.assertThat(result.name, Matchers.`is`(language.name))
        MatcherAssert.assertThat(result.designed, Matchers.`is`(language.designed))
        MatcherAssert.assertThat(result.year, Matchers.`is`(language.year))
        MatcherAssert.assertThat(result.version, Matchers.`is`(language.version))
        MatcherAssert.assertThat(result.web, Matchers.`is`(language.web))
    }

    @Test
    fun `should throw NotFoundException when language not found`() {
        val language = LANGUAGE
        every { dao.getById(any()) } returns null

        assertThrows<NotFoundException> { useCase.invoke(language) }
    }

}