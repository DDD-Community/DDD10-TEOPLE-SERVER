package com.ddd.teople.api.auth.dto

import jakarta.validation.constraints.NotBlank

data class TokenRequest(
    @field:NotBlank(message = "empty token")
    val token: String
)
