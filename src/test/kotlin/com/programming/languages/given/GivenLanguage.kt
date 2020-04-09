package com.programming.languages.given

import com.programming.languages.domain.Language
import com.programming.languages.domain.User
import com.programming.languages.repository.http.dto.Item
import com.programming.languages.repository.http.dto.LanguageGithub
import com.programming.languages.rest.dto.LanguageRequest
import com.programming.languages.rest.dto.LanguageResponse
import com.programming.languages.rest.dto.LoginRequest
import com.programming.languages.rest.dto.UserRequest
import java.util.*


interface GivenLanguage {
    val LANGUAGE
        get() = Language(
                id = 1,
                name = "Kotlin",
                designed = "JetBrains",
                year = 2013,
                version = "1.3.61",
                web = "https://kotlinlang.org",
                total = 1,
                stars = 2,
                forks = 3,
                watchers = 4,
                openIssues = 5
        )


    val NEW_LANGUAGE
        get() = Language(
                id = 2,
                name = "Java",
                designed = "James Gosling",
                year = 1995,
                version = "Java SE 14",
                web = "https://openjdk.java.net",
                total = 6,
                stars = 7,
                forks = 8,
                watchers = 9,
                openIssues = 10
        )

    val LANGUAGE_REQUEST
        get() = LanguageRequest(
                name = "Kotlin",
                version = "1.3.61",
                year = 2013,
                designed = "JetBrains",
                web = "https://kotlinlang.org"
        )

    val NEW_LANGUAGE_REQUEST
        get() = LanguageRequest(
                name = "Java",
                designed = "James Gosling",
                year = 1995,
                version = "Java SE 14",
                web = "https://openjdk.java.net"
        )

    val LANGUAGE_RESPONSE
        get() = LanguageResponse(
                id = "1",
                name = "Kotlin",
                designed = "JetBrains",
                year = 2013,
                version = "1.3.61",
                web = "https://kotlinlang.org",
                total = 1,
                stars = 2,
                forks = 3,
                watchers = 4,
                openIssues = 5
        )

    val LANGUAGE_ID
        get() = 100

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

    val USER
        get() = User(
                id = 1,
                username = "username",
                password = "password",
                email = "email@gmail.com"
        )

    val USER_REQUEST
        get() = UserRequest(
                username = "username",
                password = "password",
                email = "email@gmail.com"
        )

    val NEW_USER_REQUEST
        get() = UserRequest(
                username = "username1",
                password = "password",
                email = "email@gmail.com"
        )

    val LOGIN_REQUEST
        get() = LoginRequest(
                username = "username",
                password = "password"
        )

}