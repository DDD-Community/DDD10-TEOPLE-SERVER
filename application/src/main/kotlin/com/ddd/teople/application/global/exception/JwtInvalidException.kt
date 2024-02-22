package com.ddd.teople.application.global.exception

class JwtInvalidException: RuntimeException {
    constructor(): super()
    constructor(message: String): super(message)
}