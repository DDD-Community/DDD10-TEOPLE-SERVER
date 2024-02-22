package com.ddd.teople.bootstrap.common.handler

import com.ddd.teople.application.global.exception.CoupleCodeInvalidException
import com.ddd.teople.application.global.exception.JwtInvalidException
import com.ddd.teople.bootstrap.common.constants.ResponseCode
import com.ddd.teople.bootstrap.common.dto.CommonResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
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
    fun bindException(e: BindException): CommonResponse<Nothing> =
        CommonResponse(
            code = ResponseCode.BAD_REQUEST.code,
            message = ResponseCode.BAD_REQUEST.message,
            data = null
        ).also { log.error("[BindException] Exception occurs - message: {}", e.message, e) }

    @ExceptionHandler(JwtInvalidException::class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    fun jwtInvalidException(e: JwtInvalidException): CommonResponse<Nothing> =
        CommonResponse(
            code = ResponseCode.UNAUTHORIZED.code,
            message = if(e.message.isNullOrEmpty()) ResponseCode.UNAUTHORIZED.message else e.message!!,
            data = null
        ).also { log.error("[JwtInvalidException] Exception occurs - message: {}", e.message, e) }

    @ExceptionHandler(CoupleCodeInvalidException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun coupleCodeInvalidException(e: CoupleCodeInvalidException): CommonResponse<Nothing> =
        CommonResponse(
            code = ResponseCode.COUPLE_CODE_NOT_FOUND.code,
            message = if(e.message.isNullOrEmpty()) ResponseCode.COUPLE_CODE_NOT_FOUND.message else e.message!!,
            data = null
        ).also { log.error("[CoupleCodeInvalidException] Exception occurs - message: {}", e.message, e) }
}