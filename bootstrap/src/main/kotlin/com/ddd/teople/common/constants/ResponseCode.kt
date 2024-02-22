package com.ddd.teople.common.constants

enum class ResponseCode(
    val code: String,
    val message: String
) {
    SUCCESS("0", "Success"),
    BAD_REQUEST("400", "Bad Request")
}