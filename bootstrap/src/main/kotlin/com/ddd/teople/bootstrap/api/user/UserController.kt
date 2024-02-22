package com.ddd.teople.bootstrap.api.user

import com.ddd.teople.application.module.user.dto.RegisterUserCnd
import com.ddd.teople.application.module.user.port.`in`.RegisterUseCase
import com.ddd.teople.bootstrap.api.user.dto.RegisterRequest
import com.ddd.teople.bootstrap.api.user.dto.RegisterResponse
import com.ddd.teople.bootstrap.common.dto.CommonResponse
import org.slf4j.LoggerFactory
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val registerUseCase: RegisterUseCase
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/user")
    fun register(@Validated input: RegisterRequest): CommonResponse<RegisterResponse> {
        val cnd = RegisterUserCnd.of(
            nickName = input.nickName,
            birth = input.birth,
            anniversary = input.anniversary,
            coupleCode = input.coupleCode ?: ""
        )
        val result = registerUseCase.register(input = cnd)

        val response = RegisterResponse.of(
            coupleId = result.coupleId,
            userId = result.userId,
            coupleCode = result.coupleCode,
            accessToken = result.accessToken
        )
        return CommonResponse.success(data = response)
    }


//    @Authentication
//    @GetMapping("/user/me")
//    fun me(): CommonResponse<String> {
//        return CommonResponse.success(data = "TEST")
//    }
}