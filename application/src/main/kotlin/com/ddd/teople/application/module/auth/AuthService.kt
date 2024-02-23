package com.ddd.teople.application.module.auth

import com.ddd.teople.application.global.utils.JwtUtils
import com.ddd.teople.application.module.auth.dto.TokenInfo
import com.ddd.teople.application.module.auth.port.`in`.GenerateTokenUseCase
import org.springframework.stereotype.Service

@Service
class AuthService: GenerateTokenUseCase {

    override fun reIssueToken(token: String): TokenInfo {
        val tokenInfo = JwtUtils.verify(token = token)
        val newToken = JwtUtils.generate(
            userId = tokenInfo.userId,
            coupleId = tokenInfo.coupleId
        )

        return TokenInfo.of(token = newToken, userId = tokenInfo.userId, coupleId = tokenInfo.coupleId)
    }

    override fun issueToken(userId: String, coupleId: String): TokenInfo =
        TokenInfo.of(
            token = JwtUtils.generate(userId = userId, coupleId = coupleId),
            userId = userId,
            coupleId = coupleId
        )
}