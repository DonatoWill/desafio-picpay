package com.picpay.challenge.wallet.app.exceptionhandler

import com.picpay.challenge.wallet.app.dto.response.ErrorResponse
import commons.exception.WalletNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.logging.Logger

@ControllerAdvice
class GlobalExceptionHandler {

    private final val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(WalletNotFoundException::class)
    fun walletNotFoundHandler(exception: WalletNotFoundException): ResponseEntity<ErrorResponse>{
        logger.error(exception.message, exception)

        val error = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.reasonPhrase,
            message = exception.message
        )

        return ResponseEntity.status(error.status).body(error)
    }

    @ExceptionHandler(Exception::class)
    fun genericExceptionHandler(exception: Exception): ResponseEntity<ErrorResponse>{
        logger.error(exception.message, exception)

        val error = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            message = exception.message
        )

        return ResponseEntity.status(error.status).body(error)
    }

}