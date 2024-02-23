package com.ddd.teople.bootstrap.global.aop

import com.ddd.teople.application.global.exception.JwtInvalidException
import com.ddd.teople.application.global.utils.JwtUtils
import com.ddd.teople.bootstrap.global.common.context.AuthenticationContext
import com.ddd.teople.bootstrap.global.common.context.AuthenticationContextHolder
import com.ddd.teople.bootstrap.global.common.exception.AuthenticationException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
class AuthenticationAspect(

) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Around(value = "@annotation(com.ddd.teople.bootstrap.global.common.annotation.Authentication)")
    fun authenticationPointCut(joinPoint: ProceedingJoinPoint): Any? {
        try {
            val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request

            // Authorization Header 체크
            val authorization = request.getHeader(HttpHeaders.AUTHORIZATION) ?: throw AuthenticationException("Authorization Header Empty")

            // Bearer 타입 체크
            if(!authorization.startsWith("Bearer")) {
                throw AuthenticationException("Invalid Authorization Grant Type")
            }

            // access_token 존재여부 체크
            val accessToken = authorization.substringAfter("Bearer ").ifEmpty { throw AuthenticationException("Access Token Empty") }

            // 테스트용
            if(accessToken == "TEOPLE_TEST") {
                AuthenticationContextHolder.set(AuthenticationContext(userId = "TEOPLE_TEST", coupleId = "TEOPLE_TEST"))
            }else {
                // access_token 검증
                val tokenInfo = JwtUtils.verify(accessToken)

                // Context 설정
                AuthenticationContextHolder.set(AuthenticationContext(userId = tokenInfo.userId, coupleId = tokenInfo.coupleId))
            }

            return joinPoint.proceed()
        }catch (jie: JwtInvalidException) {
            throw jie
        }catch (ae: AuthenticationException) {
            throw ae
        }catch (e: Exception) {
            throw AuthenticationException("Exception occurs")
        }
    }
}