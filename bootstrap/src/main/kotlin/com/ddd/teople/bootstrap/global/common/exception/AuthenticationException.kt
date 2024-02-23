package com.ddd.teople.bootstrap.global.common.exception

class AuthenticationException: RuntimeException {
    constructor(): super()
    constructor(message: String): super(message)
}