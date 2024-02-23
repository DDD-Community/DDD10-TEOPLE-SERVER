package com.ddd.teople.frameworkmysql.adapter.map

import com.ddd.teople.application.module.map.dto.RegisterMapCnd
import com.ddd.teople.frameworkmysql.entity.Map
import com.ddd.teople.frameworkmysql.entity.QMap
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

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

    @Transactional
    fun register(mapId: String, input: RegisterMapCnd): Long =
        queryFactory
            .insert(map)
            .columns(
                map.mapId,
                map.coupleId,
                map.accountId,
                map.thirdMapId,
                map.latitude,
                map.longitude,
                map.createdAt
            )
            .values(
                mapId,
                input.coupleId,
                input.userId,
                input.thirdMapId,
                input.lat,
                input.lng,
                LocalDateTime.now()
            )
            .execute()

    @Transactional
    fun delete(mapId: String, coupleId: String, userId: String): Long =
        queryFactory
            .delete(map)
            .where(
                map.mapId.eq(mapId),
                map.coupleId.eq(coupleId),
                map.accountId.eq(userId)
            )
            .execute()
}