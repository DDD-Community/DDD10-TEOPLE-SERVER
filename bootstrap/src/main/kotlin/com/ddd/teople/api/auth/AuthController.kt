package com.ddd.teople.api.auth

import com.ddd.teople.module.auth.port.`in`.GenerateTokenUseCase
import com.ddd.teople.api.auth.dto.TokenRequest
import com.ddd.teople.api.auth.dto.TokenResponse
import com.ddd.teople.common.dto.CommonResponse
import com.ddd.teople.module.auth.dto.TokenInfo
import org.slf4j.LoggerFactory
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val generateTokenUseCase: GenerateTokenUseCase
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    /**
     * 토큰 재발급
     *
     * @param input
     * @return [CommonResponse]
     */
    @GetMapping("/auth/token")
    fun token(@Validated input: TokenRequest): CommonResponse<TokenResponse> {
        log.info("[token] input: $input")

        val result = generateTokenUseCase.reIssueToken(input.token)
        return CommonResponse.success(data = TokenResponse.of(input = result))
    }
}