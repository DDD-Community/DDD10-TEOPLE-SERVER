package com.ddd.teople.application.module.user.port.out

import java.time.LocalDate

interface PostUserPort {

    fun registerUser(userId: String, nickName: String, birth: LocalDate)

    fun registerCouple(coupleId: String, userId: String, anniversary: LocalDate, coupleCode: String)

    fun updateCouple(coupleId: String, userId: String)
}