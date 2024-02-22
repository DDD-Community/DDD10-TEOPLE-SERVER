package com.ddd.teople.application.module.user.port.out

import com.ddd.teople.application.module.user.dto.CoupleInfo

interface LoadUserPort {

    fun findCoupleByCode(coupleCode: String): CoupleInfo
}