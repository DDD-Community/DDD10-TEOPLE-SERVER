package com.ddd.teople.frameworkmysql.adapter.user

import com.ddd.teople.frameworkmysql.entity.Account
import com.ddd.teople.frameworkmysql.entity.Couple
import com.ddd.teople.frameworkmysql.entity.QAccount
import com.ddd.teople.frameworkmysql.entity.QCouple
import com.querydsl.jpa.impl.JPAQueryFactory
import org.hibernate.internal.util.type.PrimitiveWrapperHelper.LongDescriptor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
class UserRepository(
    @Qualifier("teopleQueryFactory")
    private val queryFactory: JPAQueryFactory
) {

    private val account = QAccount.account!!
    private val couple = QCouple.couple!!

    @Transactional
    fun registerUser(userId: String, nickName: String, birth: LocalDate): Long =
        queryFactory
            .insert(account)
            .columns(
                account.accountId,
                account.nickName,
                account.birth,
                account.createdAt
            )
            .values(
                userId,
                nickName,
                birth,
                LocalDateTime.now()
            )
            .execute()

    @Transactional(readOnly = true)
    fun findUser(userId: String): Account? =
        queryFactory
            .selectFrom(account)
            .where(account.accountId.eq(userId))
            .fetchFirst()

    @Transactional
    fun registerCouple(coupleId: String, userId: String, anniversary: LocalDate, coupleCode: String): Long =
        queryFactory
            .insert(couple)
            .columns(
                couple.coupleId,
                couple.mappingAccountId,
                couple.anniversaryDate,
                couple.coupleCode,
                couple.createdAt
            )
            .values(
                coupleId,
                userId,
                anniversary,
                coupleCode,
                LocalDateTime.now()
            )
            .execute()

    @Transactional
    fun updateCouple(coupleId: String, userId: String): Long =
        queryFactory
            .update(couple)
            .set(couple.mappedAccountId, userId)
            .where(couple.coupleId.eq(coupleId))
            .execute()

    @Transactional(readOnly = true)
    fun findCoupleByCode(coupleCode: String): Couple? =
        queryFactory
            .selectFrom(couple)
            .where(couple.coupleCode.eq(coupleCode))
            .fetchFirst()

    @Transactional(readOnly = true)
    fun findCoupleById(coupleId: String): Couple? =
        queryFactory
            .selectFrom(couple)
            .where(couple.coupleId.eq(coupleId))
            .fetchFirst()
}