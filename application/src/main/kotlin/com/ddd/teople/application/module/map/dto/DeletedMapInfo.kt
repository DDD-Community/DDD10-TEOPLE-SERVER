package com.ddd.teople.application.module.map.dto

data class DeletedMapInfo(
    val mapId: String,
    val coupleId: String,
    val userId: String
) {

    companion object {
        fun of(mapId: String, coupleId: String, userId: String): DeletedMapInfo =
            DeletedMapInfo(
                mapId = mapId,
                coupleId = coupleId,
                userId = userId
            )
    }
}