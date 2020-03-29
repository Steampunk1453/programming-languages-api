package com.programming.languages.usecase.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class EmailAlreadyUsedException(message: String) : RuntimeException(message)

