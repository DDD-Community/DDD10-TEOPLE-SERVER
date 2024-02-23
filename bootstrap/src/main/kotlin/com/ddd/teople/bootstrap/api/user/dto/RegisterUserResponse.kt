package com.ddd.teople.bootstrap.api.user.dto

import java.io.Serializable

data class RegisterUserResponse(
    val coupleId: String,
    val userId: String,
    val coupleCode: String,
    val accessToken: String
): Serializable {

    companion object {
        fun of(coupleId: String, userId: String, coupleCode: String, accessToken: String): RegisterUserResponse =
            RegisterUserResponse(
                coupleId = coupleId,
                userId = userId,
                coupleCode = coupleCode,
                accessToken = accessToken
            )
    }
}
