package com.ddd.teople.global.utils

import com.ddd.teople.application.global.utils.JwtUtils
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldBeEqualIgnoringCase

class JwtUtilsTest: BehaviorSpec({

    Given("먼저, ") {
        val userId = "TEST_USER_ID"
        val coupleId = "TEST_COUPLE_ID"

        When("만약, ") {
            val token = JwtUtils.generate(userId = userId, coupleId = coupleId)

            Then("그러면,") {
                val tokenInfo = JwtUtils.verify(token = token)

                userId shouldBeEqualIgnoringCase tokenInfo.userId
                coupleId shouldBeEqualIgnoringCase tokenInfo.coupleId
            }
        }
    }
})