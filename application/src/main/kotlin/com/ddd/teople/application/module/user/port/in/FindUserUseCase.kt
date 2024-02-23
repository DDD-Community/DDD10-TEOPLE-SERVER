package com.ddd.teople.application.module.user.port.`in`

import com.ddd.teople.application.module.user.dto.MyInfo

interface FindUserUseCase {

    fun findUser(userId: String, coupleId: String): MyInfo
}