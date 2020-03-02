package com.programming.languages.usecase

import com.programming.languages.given.GivenLanguage
import com.programming.languages.repository.LanguageDao
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
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

        MatcherAssert.assertThat(result, Matchers.`is`(Matchers.not(Matchers.nullValue())))
        MatcherAssert.assertThat(result?.id, Matchers.`is`(language.id))
        MatcherAssert.assertThat(result?.name, Matchers.`is`(language.name))
        MatcherAssert.assertThat(result?.designed, Matchers.`is`(language.designed))
        MatcherAssert.assertThat(result?.year, Matchers.`is`(language.year))
        MatcherAssert.assertThat(result?.version, Matchers.`is`(language.version))
        MatcherAssert.assertThat(result?.web, Matchers.`is`(language.web))
    }
}