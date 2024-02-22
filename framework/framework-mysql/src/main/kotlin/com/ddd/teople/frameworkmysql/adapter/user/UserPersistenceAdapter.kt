package com.ddd.teople.frameworkmysql.adapter.user

import com.ddd.teople.application.global.exception.CoupleCodeInvalidException
import com.ddd.teople.application.module.user.dto.CoupleInfo
import com.ddd.teople.application.module.user.port.out.LoadUserPort
import com.ddd.teople.application.module.user.port.out.PostUserPort
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class UserPersistenceAdapter(
    private val userRepository: UserRepository
): PostUserPort, LoadUserPort {

    override fun registerUser(userId: String, nickName: String, birth: LocalDate) {
        userRepository.registerUser(
            userId = userId,
            nickName = nickName,
            birth = birth
        )
    }

    override fun registerCouple(coupleId: String, userId: String, anniversary: LocalDate, coupleCode: String) {
        userRepository.registerCouple(
            coupleId = coupleId,
            userId = userId,
            anniversary = anniversary,
            coupleCode = coupleCode
        )
    }

    override fun updateCouple(coupleId: String, userId: String) {
        userRepository.updateCouple(coupleId = coupleId, userId = userId)
    }

    override fun findCoupleByCode(coupleCode: String): CoupleInfo =
        userRepository.findCoupleByCode(coupleCode = coupleCode)
            ?.let { CoupleInfo.of(
                coupleId = it.coupleId,
                mappingAccountId = it.mappingAccountId,
                mappedAccountId = it.mappedAccountId ?: "",
                anniversaryDate = it.anniversaryDate,
                coupleCode = it.coupleCode
            ) }
            ?: throw CoupleCodeInvalidException("Couple Code Not Found")

}