package com.programming.languages.repository

import com.programming.languages.domain.Language
import com.programming.languages.repository.entity.LanguageEntity
import com.programming.languages.repository.entity.toDomain
import com.programming.languages.repository.entity.toEntity
import com.programming.languages.repository.http.GithubLanguageRepository
import com.programming.languages.repository.http.dto.LanguageGithub
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LanguageRepository : JpaRepository<LanguageEntity, Long> {
    fun findByName(name: String?): Optional<LanguageEntity>
}

@Component
class LanguageDao(private val languageRepository: LanguageRepository,
                  private val githubLanguageRepository: GithubLanguageRepository) {

    @Value("\${github.query}")
    private lateinit var githubQuery: String

    fun create(language: Language): Language {
        return languageRepository.save(language.toEntity()).toDomain()
    }

    fun getById(id: Long): Language? {
        return languageRepository.findById(id).orElse(null)?.toDomain()
    }

    fun getByName(name: String?): Language? {
        return languageRepository.findByName(name).orElse(null)?.toDomain()
    }

    fun getAll(): List<Language>? {
       return languageRepository.findAll().map { it.toDomain() }
    }

    fun delete(id: Long) {
        languageRepository.deleteById(id)
    }

    fun getByNameFromGithub(name: String?): LanguageGithub? {
        val query = githubQuery + name
        return githubLanguageRepository.getLanguageData(query)
    }

}
