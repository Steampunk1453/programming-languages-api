package com.programming.languages.rest

import com.programming.languages.usecase.exception.AlreadyRegisteredException
import com.programming.languages.usecase.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
@RestController
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(NotFoundException::class)
    fun notFound(ex: NotFoundException) =
        ResponseEntity(LanguageError(LanguageErrorCode.NOT_FOUND, ex.message), HttpStatus.NOT_FOUND)

    @ExceptionHandler(AlreadyRegisteredException::class)
    fun alreadyExists(ex: AlreadyRegisteredException) =
        ResponseEntity(LanguageError(LanguageErrorCode.ALREADY_REGISTERED, ex.message), HttpStatus.CONFLICT)
    
}
