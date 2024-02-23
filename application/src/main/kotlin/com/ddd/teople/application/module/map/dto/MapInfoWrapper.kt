package com.ddd.teople.application.module.map.dto

data class MapInfoWrapper(
    val coupleId: String,
    val userId: String,

    val myMap: List<MapInfo>,
    val yourMap: List<MapInfo>,
    val togetherMap: List<MapInfo>
)
