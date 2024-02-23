package com.ddd.teople.frameworkmysql.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(catalog = "TEOPLE", name = "map_tb")
class Map(
    @Id
    @Column(name = "map_id", nullable = false)
    val mapId: String,

    @Column(name = "couple_id", nullable = false)
    val coupleId: String,
    @Column(name = "account_id", nullable = false)
    val accountId: String,
    @Column(name = "third_map_id", nullable = false)
    val thirdMapId: String,
    @Column(name = "latitude", nullable = false)
    val latitude: Float,
    @Column(name = "longitude", nullable = false)
    val longitude: Float,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,
)