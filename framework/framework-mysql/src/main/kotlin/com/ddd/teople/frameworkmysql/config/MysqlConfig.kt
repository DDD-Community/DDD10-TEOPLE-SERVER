package com.ddd.teople.frameworkmysql.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaDialect
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "teopleEntityManagerFactory",
    transactionManagerRef = "teopleTransactionManager",
    basePackages = ["com.ddd.teople"]
)
class MysqlConfig(
    private val env: Environment
) {

    @Bean
    fun teopleDataSource(): DataSource =
        HikariDataSource(
            HikariConfig().apply {
                driverClassName = "com.mysql.cj.jdbc.Driver"
                jdbcUrl = env.getProperty("mysql.teople.url")
                username = env.getProperty("mysql.teople.username")
                password = env.getProperty("mysql.teople.password")
                connectionTimeout = env.getProperty("spring.datasource.hikari.connection-timeout")!!.toLong()
                maximumPoolSize = env.getProperty("spring.datasource.hikari.maximum-pool-size")!!.toInt()
                minimumIdle = env.getProperty("spring.datasource.hikari.minimum-idle")!!.toInt()
            }
        )

    @Bean
    fun teopleEntityManagerFactory(): LocalContainerEntityManagerFactoryBean =
        LocalContainerEntityManagerFactoryBean().apply {
            dataSource = teopleDataSource()
            jpaVendorAdapter = HibernateJpaVendorAdapter()
            jpaDialect = HibernateJpaDialect()
            setPackagesToScan("com.ddd.teople.frameworkmysql.entity")
            setJpaPropertyMap(
                mapOf(
                    "hibernate.physical_naming_strategy" to env.getProperty("hibernate.physical_naming_strategy"),
                    "hibernate.show_sql" to env.getProperty("hibernate.show-sql"),
                    "hibernate.format_sql" to env.getProperty("hibernate.format_sql")
                )
            )
        }

    @Bean
    fun teopleTransactionManager(): JpaTransactionManager =
        JpaTransactionManager().apply { entityManagerFactory = teopleEntityManagerFactory().`object` }
}