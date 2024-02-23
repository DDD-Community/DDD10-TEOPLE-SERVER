package com.ddd.teople.application.module.map.port.`in`

import com.ddd.teople.application.module.map.dto.MapInfo

interface FindMapUseCase {

    fun findMapList(coupleId: String, userId: String): List<MapInfo>
}