package com.ddd.teople.frameworkmysql.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(catalog = "TEOPLE", name = "couple_tb")
class Couple(
    @Id
    @Column(name = "couple_id", nullable = false)
    val coupleId: String,

    @Column(name = "mapping_account_id", nullable = false)
    val mappingAccountId: String,
    @Column(name = "mapped_account_id")
    val mappedAccountId: String? = null,
    @Column(name = "anniversary_date", nullable = false)
    val anniversaryDate: LocalDate,
    @Column(name = "couple_code", nullable = false)
    val coupleCode: String,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,
)