package com.ddd.teople.module.auth.port.`in`

import com.ddd.teople.module.auth.dto.TokenInfo

interface GenerateTokenUseCase {

    /**
     * 토큰 재발급
     *
     * @param token
     * @return [String] - 재발급 토큰
     */
    fun reIssueToken(token: String): TokenInfo

    /**
     * 토큰 신규발급
     *
     * @param userId
     * @return [String] - 신규발급 토큰
     */
    fun issueToken(userId: String): TokenInfo
}