package com.ddd.teople.frameworkmysql.adapter.map

import com.ddd.teople.application.module.map.dto.MapInfo
import com.ddd.teople.application.module.map.port.out.LoadMapPort
import org.springframework.stereotype.Component

@Component
class MapPersistenceAdapter(
    private val mapRepository: MapRepository
): LoadMapPort {

    override fun findMapList(coupleId: String, userId: String): List<MapInfo> =
        mapRepository.findMapList(coupleId = coupleId, userId = userId).map { MapInfo.of(
            mapId = it.mapId,
            coupleId = it.coupleId,
            userId = it.accountId,
            thirdMapId = it.thirdMapId,
            latitude = it.latitude,
            longitude = it.longitude
        ) }
}