package com.ddd.teople.application.module.user.dto

data class RegisteredUserInfo(
    val coupleId: String,
    val userId: String,
    val coupleCode: String,
    val accessToken: String
) {

    companion object {
        fun of(coupleId: String, userId: String, coupleCode: String, accessToken: String): RegisteredUserInfo =
            RegisteredUserInfo(
                coupleId = coupleId,
                userId = userId,
                coupleCode = coupleCode,
                accessToken = accessToken
            )
    }
}