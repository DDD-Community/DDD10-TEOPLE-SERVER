package com.ddd.teople.application.module.user.port.`in`

import com.ddd.teople.application.module.user.dto.RegisterUserCnd
import com.ddd.teople.application.module.user.dto.UserInfo

interface RegisterUseCase {

    fun register(input: RegisterUserCnd): UserInfo
}