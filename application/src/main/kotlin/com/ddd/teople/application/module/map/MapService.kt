package com.ddd.teople.application.module.map

import com.ddd.teople.application.module.map.dto.MapInfo
import com.ddd.teople.application.module.map.port.`in`.FindMapUseCase
import com.ddd.teople.application.module.map.port.out.LoadMapPort
import org.springframework.stereotype.Service

@Service
class MapService(
    private val loadMapPort: LoadMapPort
): FindMapUseCase {

    override fun findMapList(coupleId: String, userId: String): List<MapInfo> =
        loadMapPort.findMapList(coupleId = coupleId, userId = userId)
}