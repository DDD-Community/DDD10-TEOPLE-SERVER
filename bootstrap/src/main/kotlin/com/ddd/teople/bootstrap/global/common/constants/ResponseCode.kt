package com.ddd.teople.bootstrap.global.common.constants

enum class ResponseCode(
    val code: String,
    val message: String
) {
    SUCCESS("0", "Success"),
    BAD_REQUEST("400", "Bad Request"),
    UNAUTHORIZED("401", "Invalid Token"),

    COUPLE_CODE_NOT_FOUND("T001", "Couple Code Not Found"),
    DATA_NOT_FOUND("T002", "Data Not Found"),
}