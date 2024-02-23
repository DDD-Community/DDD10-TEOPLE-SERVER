package com.ddd.teople.bootstrap.api.map.dto

import jakarta.validation.constraints.NotBlank

data class DeleteMapRequest(
    @field:NotBlank(message = "empty mapId")
    val mapId: String
)
