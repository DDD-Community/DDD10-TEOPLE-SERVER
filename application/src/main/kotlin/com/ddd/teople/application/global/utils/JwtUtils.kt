package com.ddd.teople.application.global.utils

import com.ddd.teople.application.global.exception.JwtInvalidException
import com.ddd.teople.application.module.auth.dto.TokenInfo
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

object JwtUtils {
    private val log = LoggerFactory.getLogger(this::class.java)

    private const val SECRET_KEY = "thisistestusersecretkeyprojectnameisdddteopleoooooooooooo"

    fun generate(userId: String, coupleId: String): String =
        Jwts.builder()
            .setIssuer("teople")
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()))
            .setClaims(mutableMapOf(
                "coupleId" to coupleId,
                "userId" to userId
            ))
            .signWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray(StandardCharsets.UTF_8)))
            .compact()

    fun verify(token: String): TokenInfo =
        try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .body

            extract(claims = claims)
        }catch (eje: ExpiredJwtException) {
            log.error("[verify] ExpiredJwtException occurs - e: $eje")
            throw JwtInvalidException("Expired JWT Token")
        }catch (e: Exception) {
            log.error("[verify] Exception occurs - e: $e")
            throw JwtInvalidException()
        }

    private fun extract(claims: Claims): TokenInfo =
        TokenInfo(
            token = "",
            userId = claims.getOrDefault("userId", "").toString(),
            coupleId = claims.getOrDefault("coupleId", "").toString()
        )

}