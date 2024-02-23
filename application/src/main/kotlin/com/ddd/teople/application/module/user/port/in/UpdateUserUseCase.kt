package com.ddd.teople.application.module.user.port.`in`

import com.ddd.teople.application.module.user.dto.UpdateUserCnd
import com.ddd.teople.application.module.user.dto.UpdatedUserInfo
import com.ddd.teople.application.module.user.dto.UserInfo

interface UpdateUserUseCase {

    fun update(input: UpdateUserCnd): UpdatedUserInfo
}