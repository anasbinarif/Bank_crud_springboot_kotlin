package com.example.banks_spring_kotlin.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CustomAccountException {
    @ExceptionHandler
    fun handleException(ex: AccountNotFound): ResponseEntity<CustomResponse>{
        val customResponse= CustomResponse(HttpStatus.NOT_FOUND.value(), ex.message, System.currentTimeMillis())
        return ResponseEntity(customResponse, HttpStatus.NOT_FOUND)
    }
}