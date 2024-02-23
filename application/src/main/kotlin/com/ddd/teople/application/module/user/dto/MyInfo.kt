package com.ddd.teople.application.module.user.dto

import com.ddd.teople.application.module.map.dto.MapInfo
import java.time.LocalDate

data class MyInfo(
    val coupleId: String,
    val anniversary: LocalDate,
    val dDay: Int,

    val myInfo: UserItem,
    val yourInfo: UserItem,

    val myMap: List<MapItem>,
    val yourMap: List<MapItem>,
    val togetherMap: List<MapItem>
) {

    data class UserItem(
        val userId: String,
        val nickName: String,
        val birth: LocalDate
    ) {
        companion object {
            fun of(input: UserInfo): UserItem =
                UserItem(
                    userId = input.userId,
                    nickName = input.nickName,
                    birth = input.birth
                )
        }
    }

    data class MapItem(
        val mapId: String,
        val thirdMapId: String,
        val latitude: Float,
        val longitude: Float
    ) {
        companion object {
            fun of(input: MapInfo): MapItem =
                MapItem(
                    mapId = input.mapId,
                    thirdMapId = input.thirdMapId,
                    latitude = input.latitude,
                    longitude = input.longitude
                )
        }
    }
}
