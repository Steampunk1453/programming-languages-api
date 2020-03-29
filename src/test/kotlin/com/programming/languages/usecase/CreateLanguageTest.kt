package com.programming.languages.usecase

import com.programming.languages.given.GivenLanguage
import com.programming.languages.repository.LanguageDao
import com.programming.languages.usecase.exception.AlreadyRegisteredException
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class CreateLanguageTest : GivenLanguage {

    @InjectMockKs
    private lateinit var useCase: CreateLanguage
    @MockK
    private lateinit var dao: LanguageDao

    @Test
    fun `should create a new language`() {
        val language = LANGUAGE
        val newLanguage = NEW_LANGUAGE
        every { dao.getByName(any()) } returns language
        every { dao.create(any()) } returns newLanguage

        val result = useCase.invoke(NEW_LANGUAGE)

        assertThat(result, `is`(not(nullValue())))
        assertThat(result.id, `is`(newLanguage.id))
        assertThat(result.name, `is`(newLanguage.name))
        assertThat(result.designed, `is`(newLanguage.designed))
        assertThat(result.year, `is`(newLanguage.year))
        assertThat(result.version, `is`(newLanguage.version))
        assertThat(result.web, `is`(newLanguage.web))
    }

    @Test
    fun `should throw AlreadyRegisteredException when create a new language`() {
        val language = LANGUAGE
        every { dao.getByName(any()) } returns language
        every { dao.create(any()) } returns language

        assertThrows<AlreadyRegisteredException> { useCase.invoke(language) }
    }

}