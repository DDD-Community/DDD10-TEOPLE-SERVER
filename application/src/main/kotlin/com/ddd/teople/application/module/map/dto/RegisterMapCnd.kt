package com.ddd.teople.application.module.map.dto

data class RegisterMapCnd(
    val userId: String,
    val coupleId: String,
    val thirdMapId: String,
    val lat: Float,
    val lng: Float
) {

    companion object {
        fun of(userId: String, coupleId: String, thirdMapId: String, lat: Float, lng: Float): RegisterMapCnd =
            RegisterMapCnd(
                userId = userId,
                coupleId = coupleId,
                thirdMapId = thirdMapId,
                lat = lat,
                lng = lng
            )
    }
}
