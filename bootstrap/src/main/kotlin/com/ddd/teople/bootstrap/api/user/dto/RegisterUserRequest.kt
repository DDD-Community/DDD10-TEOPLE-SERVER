package com.ddd.teople.bootstrap.api.user.dto

import jakarta.validation.constraints.NotBlank
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class RegisterUserRequest(
    @field:NotBlank(message = "empty nickName")
    val nickName: String,
    @field:DateTimeFormat(pattern = "yyyyMMdd")
    val birth: LocalDate,
    @field:DateTimeFormat(pattern = "yyyyMMdd")
    val anniversary: LocalDate,

    val coupleCode: String? = ""
)
