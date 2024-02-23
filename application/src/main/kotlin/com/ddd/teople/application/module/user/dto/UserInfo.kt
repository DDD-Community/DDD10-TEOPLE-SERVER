package com.ddd.teople.application.module.user.dto

import java.time.LocalDate

data class UserInfo(
    val userId: String,
    val nickName: String,
    val birth: LocalDate
) {

    companion object {
        fun of(userId: String, nickName: String, birth: LocalDate): UserInfo =
            UserInfo(
                userId = userId,
                nickName = nickName,
                birth = birth
            )
    }
}