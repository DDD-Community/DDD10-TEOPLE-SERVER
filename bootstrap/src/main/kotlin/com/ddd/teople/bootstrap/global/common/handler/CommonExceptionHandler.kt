package com.ddd.teople.bootstrap.global.common.handler

import com.ddd.teople.application.global.exception.CoupleCodeInvalidException
import com.ddd.teople.application.global.exception.DataNotFoundException
import com.ddd.teople.application.global.exception.JwtInvalidException
import com.ddd.teople.bootstrap.global.common.constants.ResponseCode
import com.ddd.teople.bootstrap.global.common.dto.CommonResponse
import com.ddd.teople.bootstrap.global.common.exception.AuthenticationException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CommonExceptionHandler {
    private val log = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(BindException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleBindException(e: BindException): CommonResponse<Nothing> =
        CommonResponse(
            code = ResponseCode.BAD_REQUEST.code,
            message = ResponseCode.BAD_REQUEST.message,
            data = null
        ).also { log.error("[BindException] Exception occurs - message: {}", e.message) }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): CommonResponse<Nothing> =
        CommonResponse(
            code = ResponseCode.BAD_REQUEST.code,
            message = ResponseCode.BAD_REQUEST.message,
            data = null
        ).also { log.error("[MethodArgumentNotValidException] Exception occurs - message: {}", e.message) }

    @ExceptionHandler(JwtInvalidException::class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    fun handleJwtInvalidException(e: JwtInvalidException): CommonResponse<Nothing> =
        CommonResponse(
            code = ResponseCode.UNAUTHORIZED.code,
            message = ResponseCode.UNAUTHORIZED.message,
            data = null
        ).also { log.error("[JwtInvalidException] Exception occurs - message: {}", e.message) }

    @ExceptionHandler(AuthenticationException::class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    fun handleAuthenticationException(e: AuthenticationException): CommonResponse<Nothing> =
        CommonResponse(
            code = ResponseCode.UNAUTHORIZED.code,
            message = ResponseCode.UNAUTHORIZED.message,
            data = null
        ).also { log.error("[AuthenticationException] Exception occurs - message: {}", e.message) }

    @ExceptionHandler(CoupleCodeInvalidException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleCoupleCodeInvalidException(e: CoupleCodeInvalidException): CommonResponse<Nothing> =
        CommonResponse(
            code = ResponseCode.COUPLE_CODE_NOT_FOUND.code,
            message = ResponseCode.COUPLE_CODE_NOT_FOUND.code,
            data = null
        ).also { log.error("[CoupleCodeInvalidException] Exception occurs - message: {}", e.message) }

    @ExceptionHandler(DataNotFoundException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleDataNotFoundException(e: DataNotFoundException): CommonResponse<Nothing> =
        CommonResponse(
            code = ResponseCode.DATA_NOT_FOUND.code,
            message = ResponseCode.DATA_NOT_FOUND.code,
            data = null
        ).also { log.error("[DataNotFoundException] Exception occurs - message: {}", e.message) }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleIllegalArgumentException(e: IllegalArgumentException): CommonResponse<Nothing> =
        CommonResponse(
            code = ResponseCode.BAD_REQUEST.code,
            message = ResponseCode.BAD_REQUEST.code,
            data = null
        ).also { log.error("[IllegalArgumentException] Exception occurs - message: {}", e.message) }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleException(e: Exception): CommonResponse<Nothing> =
        CommonResponse(
            code = ResponseCode.SERVER_ERROR.code,
            message = ResponseCode.SERVER_ERROR.code,
            data = null
        ).also { log.error("[Exception] Exception occurs - message: {}", e.message) }
}