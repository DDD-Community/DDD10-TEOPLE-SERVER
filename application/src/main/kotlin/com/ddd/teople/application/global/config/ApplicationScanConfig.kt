package com.ddd.teople.application.global.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(
    basePackages = ["com.ddd.teople.application"]
)
class ApplicationScanConfig {
}