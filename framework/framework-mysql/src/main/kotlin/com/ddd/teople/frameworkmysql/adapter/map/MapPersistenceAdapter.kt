package com.ddd.teople.frameworkmysql.adapter.map

import com.ddd.teople.application.module.map.dto.DeletedMapInfo
import com.ddd.teople.application.module.map.dto.MapInfo
import com.ddd.teople.application.module.map.dto.RegisterMapCnd
import com.ddd.teople.application.module.map.port.out.LoadMapPort
import com.ddd.teople.application.module.map.port.out.PostMapPort
import org.springframework.stereotype.Component

@Component
class MapPersistenceAdapter(
    private val mapRepository: MapRepository
): LoadMapPort, PostMapPort {

    override fun findMapList(coupleId: String, userId: String): List<MapInfo> =
        mapRepository.findMapList(coupleId = coupleId, userId = userId)
            .map { MapInfo.of(mapId = it.mapId,
            coupleId = it.coupleId,
            userId = it.accountId,
            thirdMapId = it.thirdMapId,
            latitude = it.latitude,
            longitude = it.longitude
        ) }

    override fun register(mapId: String, input: RegisterMapCnd): MapInfo {
        mapRepository.register(mapId = mapId, input = input)
        return MapInfo.of(mapId = mapId, input = input)
    }

    override fun delete(mapId: String, coupleId: String, userId: String): DeletedMapInfo {
        mapRepository.delete(mapId = mapId, coupleId = coupleId, userId = userId)
        return DeletedMapInfo.of(mapId = mapId, coupleId = coupleId, userId = userId)
    }
}