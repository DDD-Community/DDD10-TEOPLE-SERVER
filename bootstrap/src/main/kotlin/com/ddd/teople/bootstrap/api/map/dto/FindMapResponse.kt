package com.ddd.teople.bootstrap.api.map.dto

import com.ddd.teople.application.module.map.dto.MapInfo
import com.ddd.teople.application.module.map.dto.MapInfoWrapper

data class FindMapResponse(
    val coupleId: String,
    val userId: String,

    val myMap: List<MapInfo>,
    val yourMap: List<MapInfo>,
    val togetherMap: List<MapInfo>,

    val myMapCnt: Int,
    val yourMapCnt: Int,
    val togetherMapCnt: Int
) {
    companion object {
        fun of(input: MapInfoWrapper): FindMapResponse =
            FindMapResponse(
                coupleId = input.coupleId,
                userId = input.userId,

                myMap = input.myMap,
                yourMap = input.yourMap,
                togetherMap = input.togetherMap,

                myMapCnt = input.myMap.size,
                yourMapCnt = input.yourMap.size,
                togetherMapCnt = input.togetherMap.size,
            )
    }
}
