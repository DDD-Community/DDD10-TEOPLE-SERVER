package com.ddd.teople.application.module.map.port.`in`

import com.ddd.teople.application.module.map.dto.MapInfo
import com.ddd.teople.application.module.map.dto.RegisterMapCnd

interface RegisterMapUseCase {

    fun register(input: RegisterMapCnd): MapInfo
}