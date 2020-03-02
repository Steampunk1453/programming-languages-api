package com.programming.languages.repository

import org.springframework.data.repository.CrudRepository

interface LanguageRepository : CrudRepository<LanguageEntity, Long>
