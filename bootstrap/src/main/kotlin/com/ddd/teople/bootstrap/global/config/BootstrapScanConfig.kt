package com.ddd.teople.bootstrap.global.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(
    basePackages = ["com.ddd.teople.bootstrap"]
)
class BootstrapScanConfig {
}