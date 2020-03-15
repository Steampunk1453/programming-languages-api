package com.programming.languages.repository

import org.springframework.data.jpa.repository.JpaRepository

interface LanguageRepository : JpaRepository<LanguageEntity, Long>
