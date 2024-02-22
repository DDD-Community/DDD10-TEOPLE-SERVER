package com.ddd.teople.application.module.user.dto

import java.time.LocalDate

data class CoupleInfo(
    val coupleId: String,
    val mappingAccountId: String,
    val mappedAccountId: String,
    val anniversaryDate: LocalDate,
    val coupleCode: String
) {

    companion object {
        fun of(coupleId: String, mappingAccountId: String, mappedAccountId: String, anniversaryDate: LocalDate, coupleCode: String): CoupleInfo =
            CoupleInfo(
                coupleId = coupleId,
                mappingAccountId = mappingAccountId,
                mappedAccountId = mappedAccountId,
                anniversaryDate = anniversaryDate,
                coupleCode = coupleCode
            )
    }
}