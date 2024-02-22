package com.ddd.teople.application.module.user.dto

data class UserInfo(
    val coupleId: String,
    val userId: String,
    val coupleCode: String,
    val accessToken: String
) {

    companion object {
        fun of(coupleId: String, userId: String, coupleCode: String, accessToken: String): UserInfo =
            UserInfo(
                coupleId = coupleId,
                userId = userId,
                coupleCode = coupleCode,
                accessToken = accessToken
            )
    }
}