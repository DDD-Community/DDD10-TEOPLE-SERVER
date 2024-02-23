package com.ddd.teople.application.module.map.port.out

import com.ddd.teople.application.module.map.dto.MapInfo

interface LoadMapPort {

    fun findMapList(coupleId: String, userId: String): List<MapInfo>
}