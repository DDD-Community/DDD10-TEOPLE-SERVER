package com.ddd.teople.bootstrap.api.map.dto

import com.ddd.teople.application.module.map.dto.MapInfo


data class RegisterMapResponse(
    val mapId: String,
    val coupleId: String,
    val userId: String,
    val thirdMapId: String,
    val latitude: Float,
    val longitude: Float
) {
    companion object {
        fun of(input: MapInfo): RegisterMapResponse =
            RegisterMapResponse(
                mapId = input.mapId,
                coupleId = input.coupleId,
                userId = input.userId,
                thirdMapId = input.thirdMapId,
                latitude = input.latitude,
                longitude = input.longitude
            )
    }
}
