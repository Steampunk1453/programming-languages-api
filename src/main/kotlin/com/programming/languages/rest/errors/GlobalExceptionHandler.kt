package com.programming.languages.rest.errors

import com.programming.languages.usecase.exception.AlreadyRegisteredException
import com.programming.languages.usecase.exception.EmailAlreadyUsedException
import com.programming.languages.usecase.exception.LoginAlreadyUsedException
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

    @ExceptionHandler(LoginAlreadyUsedException::class)
    fun loginAlreadyUsed(ex: LoginAlreadyUsedException) =
            ResponseEntity(LanguageError(LanguageErrorCode.LOGIN_ALREADY_USED, ex.message), HttpStatus.CONFLICT)

    @ExceptionHandler(EmailAlreadyUsedException::class)
    fun emailAlreadyUsed(ex: EmailAlreadyUsedException) =
            ResponseEntity(LanguageError(LanguageErrorCode.EMAIL_ALREADY_USED, ex.message), HttpStatus.CONFLICT)
    
}
