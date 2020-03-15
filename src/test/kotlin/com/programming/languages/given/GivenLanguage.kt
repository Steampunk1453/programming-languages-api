package com.programming.languages.given

import com.programming.languages.domain.Language


interface GivenLanguage {
    val LANGUAGE
        get() = Language(
                id = 1,
                name = "Kotlin",
                designed = "JetBrains",
                year = 2013,
                version = "1.3.61",
                web = "https://kotlinlang.org"
        )
    val LANGUAGE_ID
     get() = 2
}