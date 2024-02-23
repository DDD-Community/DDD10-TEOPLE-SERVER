package com.ddd.teople.application.global.exception

class DataNotFoundException: RuntimeException {
    constructor(): super()
    constructor(message: String): super(message)
}