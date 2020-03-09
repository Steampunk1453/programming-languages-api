package com.programming.languages.usecase

import com.programming.languages.given.GivenLanguage
import com.programming.languages.repository.LanguageDao
import com.programming.languages.usecase.exception.NotFoundException
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class RemoveLanguageTest : GivenLanguage {

    @InjectMockKs
    private lateinit var useCase: RemoveLanguage
    @MockK
    private lateinit var dao: LanguageDao

    @Test
    fun `should delete a language found by id`() {
        val language = LANGUAGE
        every { dao.getById(any()) } returns language
        every { dao.delete(any()) } just Runs

        val result = useCase.invoke(1)

        verify(atLeast = 1) {  dao.delete(1) }
    }

    @Test
    fun `should throw NotFoundException when language not found`() {
        every { dao.getById(any()) } returns null

        assertThrows<NotFoundException> { useCase.invoke(1) }
    }


}