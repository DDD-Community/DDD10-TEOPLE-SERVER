package com.ddd.teople.module.auth.dto

import java.io.Serializable

data class TokenInfo(
    val token: String,
    val userId: String
): Serializable {

    companion object {
        fun of(token: String, userId: String) =
            TokenInfo(token = token, userId = userId)
    }

}
