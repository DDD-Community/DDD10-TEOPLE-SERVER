package com.ddd.teople.frameworkmysql.adapter.map

import com.ddd.teople.frameworkmysql.entity.Map
import com.ddd.teople.frameworkmysql.entity.QMap
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class MapRepository(
    @Qualifier("teopleQueryFactory")
    private val queryFactory: JPAQueryFactory
) {

    private val map = QMap.map!!

    @Transactional(readOnly = true)
    fun findMapList(coupleId: String, userId: String): List<Map> =
        queryFactory
            .selectFrom(map)
            .where(
                map.coupleId.eq(coupleId),
                map.accountId.eq(userId)
            )
            .fetch()
}