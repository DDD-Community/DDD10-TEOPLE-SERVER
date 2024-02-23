package com.ddd.teople.application.module.user

import com.ddd.teople.application.global.exception.CoupleCodeInvalidException
import com.ddd.teople.application.global.utils.CodeUtils
import com.ddd.teople.application.module.auth.port.`in`.GenerateTokenUseCase
import com.ddd.teople.application.module.map.port.`in`.FindMapUseCase
import com.ddd.teople.application.module.user.dto.*
import com.ddd.teople.application.module.user.port.`in`.FindUserUseCase
import com.ddd.teople.application.module.user.port.`in`.RegisterUserUseCase
import com.ddd.teople.application.module.user.port.`in`.UpdateUserUseCase
import com.ddd.teople.application.module.user.port.out.LoadUserPort
import com.ddd.teople.application.module.user.port.out.PostUserPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Service
class UserService(
    private val postUserPort: PostUserPort,
    private val loadUserPort: LoadUserPort,

    private val generateTokenUseCase: GenerateTokenUseCase,
    private val findMapUseCase: FindMapUseCase
): RegisterUserUseCase, FindUserUseCase, UpdateUserUseCase {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun register(input: RegisterUserCnd): RegisteredUserInfo {
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
            postUserPort.updateCoupleMappedAccountId(coupleId = coupleId, userId = userId)
        }

        // 엑세스토큰 발급
        val tokenInfo = generateTokenUseCase.issueToken(userId = userId, coupleId = coupleId)

        return RegisteredUserInfo.of(
            coupleId = coupleId,
            userId = userId,
            coupleCode = coupleCode,
            accessToken = tokenInfo.token
        )
    }

    override fun findUser(userId: String, coupleId: String): MyInfo {
        val coupleInfo = loadUserPort.findCoupleById(coupleId = coupleId)
        val dDay = ChronoUnit.DAYS.between(coupleInfo.anniversaryDate, LocalDate.now()).toInt()

        val myInfo = loadUserPort.findUserInfoById(userId = userId)
        val yourInfo = loadUserPort.findUserInfoById(userId = if(coupleInfo.mappingAccountId == userId) coupleInfo.mappedAccountId else coupleInfo.mappingAccountId)

        val mapInfo = findMapUseCase.findMapInfo(userId = userId, coupleInfo = coupleInfo)

        return MyInfo(
            coupleId = coupleInfo.coupleId,
            anniversary = coupleInfo.anniversaryDate,
            dDay = dDay,
            myInfo = MyInfo.UserItem.of(input = myInfo),
            yourInfo = MyInfo.UserItem.of(input = yourInfo),

            myMap = mapInfo.myMap.map { MyInfo.MapItem.of(input = it) },
            yourMap = mapInfo.yourMap.map { MyInfo.MapItem.of(input = it) },
            togetherMap = mapInfo.togetherMap.map { MyInfo.MapItem.of(input = it) }
        )
    }

    override fun findCouple(coupleId: String): CoupleInfo =
        loadUserPort.findCoupleById(coupleId = coupleId)

    override fun update(input: UpdateUserCnd): UpdatedUserInfo {
        if(!input.nickName.isNullOrEmpty()) {
            postUserPort.updateUserNickName(userId = input.userId, nickName = input.nickName)
        }

        if(input.birth != null) {
            postUserPort.updateUserBirth(userId = input.userId, birth = input.birth)
        }

        if(input.anniversary != null) {
            postUserPort.updateCoupleAnniversary(coupleId = input.coupleId, anniversary = input.anniversary)
        }

        val coupleInfo = loadUserPort.findCoupleById(coupleId = input.coupleId)
        val userInfo = loadUserPort.findUserInfoById(userId = input.userId)

        return UpdatedUserInfo.of(
            nickName = userInfo.nickName,
            birth = userInfo.birth,
            anniversary = coupleInfo.anniversaryDate
        )
    }
}