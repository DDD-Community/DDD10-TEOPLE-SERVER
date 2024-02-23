package com.ddd.teople.application.module.auth.dto

import java.io.Serializable

data class TokenInfo(
    val token: String,
    val userId: String,
    val coupleId: String
): Serializable {

    companion object {
        fun of(token: String, userId: String, coupleId: String): TokenInfo =
            TokenInfo(
                token = token,
                userId = userId,
                coupleId = coupleId
            )
    }
}
