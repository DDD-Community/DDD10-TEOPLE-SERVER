package com.ddd.teople.application.module.map.port.`in`

import com.ddd.teople.application.module.map.dto.DeletedMapInfo

interface DeleteMapUseCase {

    fun delete(mapId: String, coupleId: String, userId: String): DeletedMapInfo
}