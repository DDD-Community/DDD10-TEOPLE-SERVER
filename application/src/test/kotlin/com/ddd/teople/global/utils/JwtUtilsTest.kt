package com.ddd.teople.global.utils

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldBeEqualIgnoringCase

class JwtUtilsTest: BehaviorSpec({

    Given("먼저, ") {
        val userId = "TEST_USER_ID"

        When("만약, ") {
            val token = JwtUtils.generate(userId = userId)

            Then("그러면,") {
                val parseUserId = JwtUtils.parse(token = token)

                userId shouldBeEqualIgnoringCase parseUserId
            }
        }
    }
})