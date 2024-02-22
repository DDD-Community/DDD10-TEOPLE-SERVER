package com.ddd.teople.application.module.user

import com.ddd.teople.application.global.exception.CoupleCodeInvalidException
import com.ddd.teople.application.global.utils.CodeUtils
import com.ddd.teople.application.global.utils.JwtUtils
import com.ddd.teople.application.module.user.dto.RegisterUserCnd
import com.ddd.teople.application.module.user.dto.UserInfo
import com.ddd.teople.application.module.user.port.`in`.RegisterUseCase
import com.ddd.teople.application.module.user.port.out.LoadUserPort
import com.ddd.teople.application.module.user.port.out.PostUserPort
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Service

@Service
class UserService(
    private val postUserPort: PostUserPort,
    private val loadUserPort: LoadUserPort
): RegisterUseCase {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun register(input: RegisterUserCnd): UserInfo {
        // 사용자 등록
        val userId = CodeUtils.generateId(digitNum = 20, randUpperCase = false)
        postUserPort.registerUser(userId = userId, nickName = input.nickName, birth = input.birth)

        lateinit var coupleId: String
        lateinit var coupleCode: String
        if(input.coupleCode.isEmpty()) {
            // 신규 커플 등록 ( 한쪽만 등록 )
            coupleCode = CodeUtils.generateId(digitNum = 8, randUpperCase = true)
            coupleId = CodeUtils.generateId(digitNum = 20, randUpperCase = false)

            postUserPort.registerCouple(
                coupleId = coupleId,
                userId = userId,
                anniversary = input.anniversary,
                coupleCode = coupleCode
            )
        }else {
            // 기존 커플데이터 업데이트 ( 양쪽 등록 )
            val coupleInfo = loadUserPort.findCoupleByCode(coupleCode = input.coupleCode)

            // 이미 매핑된 커플코드인지 확인
            if(coupleInfo.mappedAccountId.isNotEmpty()) {
                throw CoupleCodeInvalidException("Already Mapped Couple Code")
            }

            coupleId = coupleInfo.coupleId
            coupleCode = coupleInfo.coupleCode
            postUserPort.updateCouple(coupleId = coupleId, userId = userId)
        }

        // 엑세스토큰 발급
        val token = JwtUtils.generate(userId = userId)

        return UserInfo.of(
            coupleId = coupleId,
            userId = userId,
            coupleCode = coupleCode,
            accessToken = token
        )
    }
}