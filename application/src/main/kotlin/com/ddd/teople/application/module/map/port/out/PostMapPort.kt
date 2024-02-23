package com.ddd.teople.application.module.map.port.out

import com.ddd.teople.application.module.map.dto.DeletedMapInfo
import com.ddd.teople.application.module.map.dto.MapInfo
import com.ddd.teople.application.module.map.dto.RegisterMapCnd

interface PostMapPort {

    fun register(mapId: String, input: RegisterMapCnd): MapInfo

    fun delete(mapId: String, coupleId: String, userId: String): DeletedMapInfo
}