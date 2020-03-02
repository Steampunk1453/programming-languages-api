package com.programming.languages.usecase

import com.programming.languages.given.GivenLanguage
import com.programming.languages.repository.LanguageDao
import com.programming.languages.repository.LanguageRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockKExtension::class)
internal class CreateLanguageTest : GivenLanguage {

    @InjectMockKs
    private lateinit var useCase: CreateLanguage
    @MockK
    private lateinit var dao: LanguageDao

    @Test
    fun `should create a new language`() {
        val language = LANGUAGE
        every { dao.create(any()) } returns language

        val result = useCase.invoke(LANGUAGE)

        assertThat(result, `is`(not(nullValue())))
        assertThat(result.id, `is`(language.id))
        assertThat(result.name, `is`(language.name))
        assertThat(result.designed, `is`(language.designed))
        assertThat(result.year, `is`(language.year))
        assertThat(result.version, `is`(language.version))
        assertThat(result.web, `is`(language.web))
    }
}