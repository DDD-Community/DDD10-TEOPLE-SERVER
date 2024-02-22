package com.ddd.teople.common.handler

import com.ddd.teople.common.constants.ResponseCode
import com.ddd.teople.common.dto.CommonResponse
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

}