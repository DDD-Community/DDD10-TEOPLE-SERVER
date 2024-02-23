package com.ddd.teople.bootstrap

import com.ddd.teople.application.global.config.ApplicationScanConfig
import com.ddd.teople.bootstrap.global.config.BootstrapScanConfig
import com.ddd.teople.frameworkmysql.config.FrameworkMySqlScanConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(
    value = [
        ApplicationScanConfig::class,
        BootstrapScanConfig::class,
        FrameworkMySqlScanConfig::class
    ]
)
class BootstrapApplication

fun main(args: Array<String>) {
    runApplication<BootstrapApplication>(*args)
}
