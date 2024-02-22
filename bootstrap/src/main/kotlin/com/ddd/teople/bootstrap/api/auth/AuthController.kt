package com.ddd.teople.bootstrap.api.auth

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(

) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/auth/token")
    fun token() {

    }
}