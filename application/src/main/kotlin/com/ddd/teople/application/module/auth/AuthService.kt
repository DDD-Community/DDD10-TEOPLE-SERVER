package com.ddd.teople.application.module.auth

import com.ddd.teople.application.global.utils.JwtUtils
import com.ddd.teople.application.module.auth.dto.TokenInfo
import com.ddd.teople.application.module.auth.port.`in`.GenerateTokenUseCase
import org.springframework.stereotype.Service

@Service
class AuthService: GenerateTokenUseCase {

    override fun reIssueToken(token: String): TokenInfo {
        val userId = JwtUtils.parse(token = token)
        return TokenInfo.of(token = JwtUtils.generate(userId = userId), userId = userId)
    }

    override fun issueToken(userId: String): TokenInfo =
        TokenInfo.of(token = JwtUtils.generate(userId = userId), userId = userId)
}