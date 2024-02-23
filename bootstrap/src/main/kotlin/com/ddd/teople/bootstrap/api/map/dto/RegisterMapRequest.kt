package com.ddd.teople.bootstrap.api.map.dto

import jakarta.validation.constraints.NotBlank

data class RegisterMapRequest(
    @field:NotBlank(message = "empty mapId")
    val mapId: String,
    val lat: Float,
    val lng: Float
)
