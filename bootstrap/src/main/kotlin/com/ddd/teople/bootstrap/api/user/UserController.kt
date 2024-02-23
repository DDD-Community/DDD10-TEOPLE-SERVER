package com.ddd.teople.bootstrap.api.user

import com.ddd.teople.application.module.user.dto.RegisterUserCnd
import com.ddd.teople.application.module.user.dto.UpdateUserCnd
import com.ddd.teople.application.module.user.dto.UserInfo
import com.ddd.teople.application.module.user.port.`in`.FindUserUseCase
import com.ddd.teople.application.module.user.port.`in`.RegisterUserUseCase
import com.ddd.teople.application.module.user.port.`in`.UpdateUserUseCase
import com.ddd.teople.bootstrap.api.user.dto.*
import com.ddd.teople.bootstrap.global.common.annotation.Authentication
import com.ddd.teople.bootstrap.global.common.context.AuthenticationContextHolder
import com.ddd.teople.bootstrap.global.common.dto.CommonResponse
import org.slf4j.LoggerFactory
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val registerUserUseCase: RegisterUserUseCase,
    private val findUserUseCase: FindUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/user")
    fun register(@Validated input: RegisterUserRequest): CommonResponse<RegisterUserResponse> {
        val cnd = RegisterUserCnd.of(
            nickName = input.nickName,
            birth = input.birth,
            anniversary = input.anniversary,
            coupleCode = input.coupleCode ?: ""
        )
        val result = registerUserUseCase.register(input = cnd)

        val response = RegisterUserResponse.of(
            coupleId = result.coupleId,
            userId = result.userId,
            coupleCode = result.coupleCode,
            accessToken = result.accessToken
        )
        return CommonResponse.success(data = response)
    }

    @Authentication
    @GetMapping("/user/me")
    fun me(): CommonResponse<FindMeResponse> {
        val userId = AuthenticationContextHolder.get().userId!!
        val coupleId = AuthenticationContextHolder.get().coupleId!!

        val result = findUserUseCase.findUser(userId = userId, coupleId = coupleId)
        return CommonResponse.success(data = FindMeResponse.of(input = result))
    }

    @Authentication
    @PatchMapping("/user")
    fun update(input: UpdateUserRequest): CommonResponse<UpdateUserResponse> {
        val userId = AuthenticationContextHolder.get().userId!!
        val coupleId = AuthenticationContextHolder.get().coupleId!!

        val cnd = UpdateUserCnd.of(
            coupleId = coupleId,
            userId = userId,
            nickName = input.nickName,
            birth = input.birth,
            anniversary = input.anniversary
        )
        val result = updateUserUseCase.update(input = cnd)
        return CommonResponse.success(data = UpdateUserResponse.of(input = result))
    }
}