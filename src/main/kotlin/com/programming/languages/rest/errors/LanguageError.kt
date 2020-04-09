package com.programming.languages.rest.errors


data class LanguageError(
        val errorCode: LanguageErrorCode?,
        val message: String?
)

enum class LanguageErrorCode {
    NOT_FOUND,
    ALREADY_REGISTERED,
    LOGIN_ALREADY_USED,
    EMAIL_ALREADY_USED
}
