package com.ddd.teople.bootstrap.api.user.dto

import com.ddd.teople.application.module.user.dto.UpdatedUserInfo
import com.ddd.teople.application.module.user.dto.UserInfo
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class UpdateUserResponse(
    val nickName: String,
    val birth: LocalDate,
    val anniversary: LocalDate,
) {
    companion object {
        fun of(input: UpdatedUserInfo): UpdateUserResponse =
            UpdateUserResponse(
                nickName = input.nickName,
                birth = input.birth,
                anniversary = input.anniversary
            )
    }
}
