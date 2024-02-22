package com.ddd.teople.frameworkmysql.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(catalog = "TEOPLE", name = "account_tb")
class Account(
    @Id
    @Column(name = "account_id", nullable = false)
    val accountId: String,
    @Column(name = "nick_name", nullable = false)
    val nickName: String,
    @Column(name = "birth", nullable = false)
    val birth: LocalDate,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,
)