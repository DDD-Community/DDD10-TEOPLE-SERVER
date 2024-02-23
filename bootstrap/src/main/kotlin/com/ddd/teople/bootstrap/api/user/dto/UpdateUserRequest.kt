package com.ddd.teople.bootstrap.api.user.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class UpdateUserRequest(
    val nickName: String? = "",
    @field:DateTimeFormat(pattern = "yyyyMMdd")
    val birth: LocalDate? = null,
    @field:DateTimeFormat(pattern = "yyyyMMdd")
    val anniversary: LocalDate? = null,
)
