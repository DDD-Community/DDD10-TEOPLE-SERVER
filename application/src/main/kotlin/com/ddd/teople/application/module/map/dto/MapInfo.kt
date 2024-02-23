package com.ddd.teople.application.module.map.dto

data class MapInfo(
    val mapId: String,
    val coupleId: String,
    val userId: String,
    val thirdMapId: String,
    val latitude: Float,
    val longitude: Float
) {

    companion object {
        fun of(mapId: String, coupleId: String, userId: String, thirdMapId: String, latitude: Float, longitude: Float): MapInfo =
            MapInfo(
                mapId = mapId,
                coupleId = coupleId,
                userId = userId,
                thirdMapId = thirdMapId,
                latitude = latitude,
                longitude = longitude
            )

        fun of(mapId: String, input: RegisterMapCnd): MapInfo =
            MapInfo(
                mapId = mapId,
                coupleId = input.coupleId,
                userId = input.userId,
                thirdMapId = input.thirdMapId,
                latitude = input.lat,
                longitude = input.lng
            )
    }
}