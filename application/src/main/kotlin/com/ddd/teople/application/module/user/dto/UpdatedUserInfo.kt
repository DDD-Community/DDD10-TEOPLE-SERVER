package com.ddd.teople.application.module.user.dto

import java.time.LocalDate

data class UpdatedUserInfo(
    val nickName: String,
    val birth: LocalDate,
    val anniversary: LocalDate,
) {

    companion object {
        fun of(nickName: String, birth: LocalDate, anniversary: LocalDate): UpdatedUserInfo =
            UpdatedUserInfo(
                nickName = nickName,
                birth = birth,
                anniversary = anniversary
            )
    }
}