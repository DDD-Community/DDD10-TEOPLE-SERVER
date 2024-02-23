package com.ddd.teople.application.module.map

import com.ddd.teople.application.global.utils.CodeUtils
import com.ddd.teople.application.module.map.dto.DeletedMapInfo
import com.ddd.teople.application.module.map.dto.MapInfo
import com.ddd.teople.application.module.map.dto.MapInfoWrapper
import com.ddd.teople.application.module.map.dto.RegisterMapCnd
import com.ddd.teople.application.module.map.port.`in`.DeleteMapUseCase
import com.ddd.teople.application.module.map.port.`in`.FindMapUseCase
import com.ddd.teople.application.module.map.port.`in`.RegisterMapUseCase
import com.ddd.teople.application.module.map.port.out.LoadMapPort
import com.ddd.teople.application.module.map.port.out.PostMapPort
import com.ddd.teople.application.module.user.dto.CoupleInfo
import com.ddd.teople.application.module.user.port.`in`.FindUserUseCase
import org.springframework.stereotype.Service

@Service
class MapService(
    private val loadMapPort: LoadMapPort,
    private val postMapPort: PostMapPort,
): FindMapUseCase, RegisterMapUseCase, DeleteMapUseCase {

    override fun findMapInfo(userId: String, coupleInfo: CoupleInfo): MapInfoWrapper {

        val myMap = loadMapPort.findMapList(coupleId = coupleInfo.coupleId, userId = userId)
        val yourMap = loadMapPort.findMapList(
            coupleId = coupleInfo.coupleId,
            userId = if(coupleInfo.mappingAccountId == userId) coupleInfo.mappedAccountId else coupleInfo.mappingAccountId
        )
        val togetherMap = myMap.filter { mine -> yourMap.map { yours -> yours.thirdMapId }.contains(mine.thirdMapId) }

        return MapInfoWrapper(
            coupleId = coupleInfo.coupleId,
            userId = userId,
            myMap = myMap,
            yourMap = yourMap,
            togetherMap = togetherMap
        )
    }

    override fun register(input: RegisterMapCnd): MapInfo {
        val mapId = CodeUtils.generateId(digitNum = 20, randUpperCase = false)
        return postMapPort.register(mapId = mapId, input = input)
    }

    override fun delete(mapId: String, coupleId: String, userId: String): DeletedMapInfo =
        postMapPort.delete(mapId = mapId, coupleId = coupleId, userId = userId)

}