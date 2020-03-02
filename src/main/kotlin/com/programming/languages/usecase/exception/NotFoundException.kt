package com.programming.languages.usecase.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class NotFoundException(message: String) : RuntimeException(message)