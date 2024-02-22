package com.ddd.teople.bootstrap.api.auth.dto

import jakarta.validation.constraints.NotBlank

data class TokenRequest(
    @field:NotBlank(message = "empty token")
    val token: String
)
