package com.programming.languages.repository.http

import com.programming.languages.repository.http.dto.LanguageGithub
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

@Repository
class GithubLanguageRepository(private val restTemplate: RestTemplate)  {

    @Value("\${github.url}")
    private lateinit var githubUrl: String

    private val logger = KotlinLogging.logger {}

    fun getLanguageData(query: String): LanguageGithub? {
        logger.debug { "URL $githubUrl$query" }
        return restTemplate.getForEntity(
                githubUrl + query,
                LanguageGithub::class.java).body
    }

}