package com.ddd.teople.api.auth.dto

import com.ddd.teople.module.auth.dto.TokenInfo
import java.io.Serializable

data class TokenResponse(
    val token: String,
    val userId: String
): Serializable {

    companion object {
        fun of(input: TokenInfo): TokenResponse =
            TokenResponse(
                token = input.token,
                userId = input.userId
            )
    }
}
