package com.programming.languages.rest


data class LanguageError(
    val errorCode: LanguageErrorCode?,
    val message: String?
)

enum class LanguageErrorCode {
    NOT_FOUND,
    ALREADY_REGISTERED
}
