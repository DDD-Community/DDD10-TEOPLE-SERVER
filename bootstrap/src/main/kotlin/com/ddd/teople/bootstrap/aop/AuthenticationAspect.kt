package com.ddd.teople.bootstrap.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
class AuthenticationAspect(

) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Around(value = "@annotation(com.ddd.teople.bootstrap.common.annotation.Authentication)")
    fun authenticationPointCut(joinPoint: ProceedingJoinPoint) {
        log.info("[authenticationPointCut] Authentication!!!!!!!!!")

        joinPoint.proceed()
    }
}