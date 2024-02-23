package com.ddd.teople.application.module.user.port.out

import com.ddd.teople.application.module.user.dto.CoupleInfo
import com.ddd.teople.application.module.user.dto.UserInfo

interface LoadUserPort {

    fun findCoupleByCode(coupleCode: String): CoupleInfo

    fun findCoupleById(coupleId: String): CoupleInfo

    fun findUserInfoById(userId: String): UserInfo
}