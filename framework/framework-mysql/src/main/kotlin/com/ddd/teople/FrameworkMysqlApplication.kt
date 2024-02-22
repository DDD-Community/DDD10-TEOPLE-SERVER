package com.ddd.teople

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FrameworkMysqlApplication

fun main(args: Array<String>) {
    runApplication<FrameworkMysqlApplication>(*args)
}
