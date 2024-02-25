import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("com.google.cloud.tools.jib") version "3.3.2"    // JIB 설정
}

dependencies {
    implementation(project(":application"))
    implementation(project(":framework:framework-mysql"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
}

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = true
jar.enabled = false

// JIB 설정
val tagName = project.properties["tagName"] ?: ""
val regex = Regex("^v")

jib {
    from {
        image = "eclipse-temurin:17"
    }
    to {
        image = "johnpark0921/teople:$tagName"
        if(regex.containsMatchIn(tagName as String)) {
            tags = setOf("latest")
        }
    }
    container {
        labels.set(
            mapOf(
                "maintainer" to "yoonho <qkrdbsgh0921@gmail.com>, mina <minaaa5814@gmail.com>"
            )
        )
        creationTime.set("USE_CURRENT_TIMESTAMP")
        setFormat("OCI")
        environment = mapOf(
            "TZ" to "Asia/Seoul"
        )
        jvmFlags = listOf(
            "-Dsun.net.inetaddr.ttl=0",     // DNS cache TTL
            "-XX:+PrintCommandLineFlags",   // Print JVM Flags
            "-Dspring.profiles.active=deploy"     // profile 설정
        )
    }
}