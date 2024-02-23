package com.ddd.teople.application.module.map.port.`in`

import com.ddd.teople.application.module.map.dto.MapInfoWrapper
import com.ddd.teople.application.module.user.dto.CoupleInfo

interface FindMapUseCase {
    fun findMapInfo(userId: String, coupleInfo: CoupleInfo): MapInfoWrapper
}