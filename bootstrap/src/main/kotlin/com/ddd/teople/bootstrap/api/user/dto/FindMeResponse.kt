package com.ddd.teople.bootstrap.api.user.dto

import com.ddd.teople.application.module.user.dto.MyInfo
import java.io.Serializable
import java.time.LocalDate

data class FindMeResponse(
    val coupleId: String,
    val anniversary: LocalDate,
    val dDay: Int,

    val myInfo: UserItem,
    val yourInfo: UserItem,

    val myMap: List<MapItem>,
    val yourMap: List<MapItem>,
    val togetherMap: List<MapItem>,

    val myMapCnt: Int,
    val yourMapCnt: Int,
    val togetherMapCnt: Int,
): Serializable {

    data class UserItem(
        val userId: String,
        val nickName: String,
        val birth: LocalDate
    ): Serializable {
        companion object {
            fun of(input: MyInfo.UserItem): UserItem =
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
    ): Serializable {
        companion object {
            fun of(input: MyInfo.MapItem): MapItem =
                MapItem(
                    mapId = input.mapId,
                    thirdMapId = input.thirdMapId,
                    latitude = input.latitude,
                    longitude = input.longitude
                )
        }
    }

    companion object {
        fun of(input: MyInfo): FindMeResponse =
            FindMeResponse(
                coupleId = input.coupleId,
                anniversary = input.anniversary,
                dDay = input.dDay,
                myInfo = UserItem.of(input.myInfo),
                yourInfo = UserItem.of(input.yourInfo),
                myMap = input.myMap.map { MapItem.of(input = it) },
                yourMap = input.yourMap.map { MapItem.of(input = it) },
                togetherMap = input.togetherMap.map { MapItem.of(input = it) },

                myMapCnt = input.myMap.size,
                yourMapCnt = input.yourMap.size,
                togetherMapCnt = input.togetherMap.size
            )
    }
}
