package com.ddd.teople.application.global.exception

class CoupleCodeInvalidException: RuntimeException {
    constructor(): super()
    constructor(message: String): super(message)
}