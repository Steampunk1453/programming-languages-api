package com.programming.languages.repository.http

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

@Repository
class GithubLanguageRepository(
        private val restTemplate: RestTemplate
) : ApiLanguageRepository {

    @Value("\${github.url}")
    private lateinit var githubUrl: String

    private val logger = KotlinLogging.logger {}

    override fun getLanguageData(query: String): LanguageGithub? {
        logger.debug { "URL $githubUrl$query" }
        return restTemplate.getForEntity(
                githubUrl + query,
                LanguageGithub::class.java).body
    }

}