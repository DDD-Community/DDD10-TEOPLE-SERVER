package com.ddd.teople.bootstrap.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(
    basePackages = ["com.ddd.teople.bootstrap"]
)
class BootstrapScanConfig {
}