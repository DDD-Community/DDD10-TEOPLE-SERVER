package com.ddd.teople.bootstrap.global.common.dto

import com.ddd.teople.bootstrap.global.common.constants.ResponseCode
import java.io.Serializable

data class CommonResponse<T> (
    val code: String,
    val message: String,
    val data: T?
): Serializable {

    companion object {
        fun <T> success(data: T): CommonResponse<T> =
            CommonResponse(
                code = ResponseCode.SUCCESS.code,
                message = ResponseCode.SUCCESS.message,
                data = data
            )
    }
}