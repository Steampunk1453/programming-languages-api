package com.programming.languages.repository

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LanguageRepository : JpaRepository<LanguageEntity, Long> {
    fun findByName(name: String?): Optional<LanguageEntity>
}

