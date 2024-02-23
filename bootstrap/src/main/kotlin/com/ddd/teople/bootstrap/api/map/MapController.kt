package com.ddd.teople.bootstrap.api.map

import com.ddd.teople.application.module.map.dto.RegisterMapCnd
import com.ddd.teople.application.module.map.port.`in`.DeleteMapUseCase
import com.ddd.teople.application.module.map.port.`in`.FindMapUseCase
import com.ddd.teople.application.module.map.port.`in`.RegisterMapUseCase
import com.ddd.teople.application.module.user.port.`in`.FindUserUseCase
import com.ddd.teople.bootstrap.api.map.dto.*
import com.ddd.teople.bootstrap.global.common.annotation.Authentication
import com.ddd.teople.bootstrap.global.common.context.AuthenticationContextHolder
import com.ddd.teople.bootstrap.global.common.dto.CommonResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MapController(
    private val registerMapUseCase: RegisterMapUseCase,
    private val deleteMapUseCase: DeleteMapUseCase,
    private val findMapUseCase: FindMapUseCase,

    private val findUserUseCase: FindUserUseCase
) {

    @Authentication
    @PostMapping("/map")
    fun register(@Validated input: RegisterMapRequest): CommonResponse<RegisterMapResponse> {
        val userId = AuthenticationContextHolder.get().userId!!
        val coupleId = AuthenticationContextHolder.get().coupleId!!

        val cnd = RegisterMapCnd.of(
            userId = userId,
            coupleId = coupleId,
            thirdMapId = input.mapId,
            lat = input.lat,
            lng = input.lng
        )
        val result = registerMapUseCase.register(input = cnd)
        return CommonResponse.success(data = RegisterMapResponse.of(input = result))
    }

    @Authentication
    @DeleteMapping("/map")
    fun delete(@Validated input: DeleteMapRequest): CommonResponse<DeleteMapResponse> {
        val userId = AuthenticationContextHolder.get().userId!!
        val coupleId = AuthenticationContextHolder.get().coupleId!!

        val result = deleteMapUseCase.delete(mapId = input.mapId, userId = userId, coupleId = coupleId)
        return CommonResponse.success(data = DeleteMapResponse(mapId = result.mapId))
    }

    @Authentication
    @GetMapping("/map")
    fun find(): CommonResponse<FindMapResponse> {
        val userId = AuthenticationContextHolder.get().userId!!
        val coupleId = AuthenticationContextHolder.get().coupleId!!

        val coupleInfo = findUserUseCase.findCouple(coupleId = coupleId)
        val result = findMapUseCase.findMapInfo(userId = userId, coupleInfo = coupleInfo)
        return CommonResponse.success(data = FindMapResponse.of(input = result))
    }
}