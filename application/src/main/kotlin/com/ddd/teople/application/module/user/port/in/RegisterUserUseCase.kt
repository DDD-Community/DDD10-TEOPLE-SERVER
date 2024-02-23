package com.ddd.teople.application.module.user.port.`in`

import com.ddd.teople.application.module.user.dto.RegisterUserCnd
import com.ddd.teople.application.module.user.dto.RegisteredUserInfo

interface RegisterUserUseCase {

    fun register(input: RegisterUserCnd): RegisteredUserInfo
}