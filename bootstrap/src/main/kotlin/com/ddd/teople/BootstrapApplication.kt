package com.ddd.teople

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BootstrapApplication

fun main(args: Array<String>) {
    runApplication<BootstrapApplication>(*args)
}
