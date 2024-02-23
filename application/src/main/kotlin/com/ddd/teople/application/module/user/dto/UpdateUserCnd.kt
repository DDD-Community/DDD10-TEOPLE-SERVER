package com.ddd.teople.application.module.user.dto

import java.time.LocalDate

data class UpdateUserCnd(
    val coupleId: String,
    val userId: String,
    val nickName: String?,
    val birth: LocalDate?,
    val anniversary: LocalDate?,
) {

    companion object {
        fun of(coupleId: String, userId: String, nickName: String?, birth: LocalDate?, anniversary: LocalDate?): UpdateUserCnd =
            UpdateUserCnd(
                coupleId = coupleId,
                userId = userId,
                nickName = nickName,
                birth = birth,
                anniversary = anniversary
            )
    }
}
