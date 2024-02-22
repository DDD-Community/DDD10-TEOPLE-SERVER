package com.ddd.teople.frameworkmysql.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueryDslConfig {
    @PersistenceContext(unitName = "teopleEntityManagerFactory")
    lateinit var teopleEntityManager: EntityManager

    @Bean
    fun teopleQueryFactory() = JPAQueryFactory(teopleEntityManager)
}