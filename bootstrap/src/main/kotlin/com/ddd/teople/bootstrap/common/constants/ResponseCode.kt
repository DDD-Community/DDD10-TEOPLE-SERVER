package com.ddd.teople.bootstrap.common.constants

enum class ResponseCode(
    val code: String,
    val message: String
) {
    SUCCESS("0", "Success"),
    BAD_REQUEST("400", "Bad Request"),
    UNAUTHORIZED("401", "Invalid Token"),

    COUPLE_CODE_NOT_FOUND("T001", "Couple Code Not Found")
}