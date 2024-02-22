package com.ddd.teople.global.utils

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

    fun generate(userId: String): String =
        Jwts.builder()
            .setIssuer("teople")
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date.from(LocalDateTime.now().plusDays(30).atZone(ZoneId.systemDefault()).toInstant()))
            .setSubject(userId)
            .signWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray(StandardCharsets.UTF_8)))
            .compact()

    fun parse(token: String): String =
        try {
            Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .body.subject
        }catch (eje: ExpiredJwtException) {
            log.error("[parse] ExpiredJwtException occurs - e: $eje")
            throw eje
        }catch (e: Exception) {
            log.error("[parse] Exception occurs - e: $e")
            throw e
        }
}