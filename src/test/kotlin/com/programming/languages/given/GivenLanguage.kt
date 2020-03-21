package com.programming.languages.given

import com.programming.languages.domain.Language
import com.programming.languages.repository.http.Item
import com.programming.languages.repository.http.LanguageGithub


interface GivenLanguage {
    val LANGUAGE
        get() = Language(
                id = 1,
                name = "Kotlin",
                designed = "JetBrains",
                year = 2013,
                version = "1.3.61",
                web = "https://kotlinlang.org",
                total = null,
                stars = null,
                forks = null,
                watchers = null,
                openIssues = null
        )
    val LANGUAGE_ID
        get() = 2

    val LANGUAGE_DATA
        get() = LanguageGithub(
                total = 1,
                items = listOf(ITEM)

        )

    val ITEM
        get() = Item(
                stars = 2,
                forks = 3,
                watchers = 4,
                openIssues = 5
        )

    val LANGUAGE_NAME
        get() = "Spring"
}