package com.ddd.teople.application.module.user.dto

import java.time.LocalDate

data class RegisterUserCnd(
    val nickName: String,
    val birth: LocalDate,
    val anniversary: LocalDate,
    val coupleCode: String
) {

    companion object {
        fun of(nickName: String, birth: LocalDate, anniversary: LocalDate, coupleCode: String): RegisterUserCnd =
            RegisterUserCnd(
                nickName = nickName,
                birth = birth,
                anniversary = anniversary,
                coupleCode = coupleCode
            )
    }
}
