package com.ddd.teople.api.auth

import com.ddd.teople.global.utils.JwtUtils
import com.ddd.teople.BootstrapApplicationTests
import io.kotest.assertions.print.print
import io.kotest.core.spec.style.BehaviorSpec
import org.slf4j.LoggerFactory
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@SpringBootTest(classes = [BootstrapApplicationTests::class])
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTest(
    private val mockMvc: MockMvc,
): BehaviorSpec({
    val log = LoggerFactory.getLogger(this::class.java)

    Given("먼저, 성공") {
        val token = JwtUtils.generate(userId = "TEST_USER_ID")

        When("만약, ") {
            mockMvc.get("/auth/token") {
                param("token", token)
            }.andDo { print() }.andReturn()

            Then("그러면,") {

            }
        }
    }

    Given("먼저, 실패") {
        val token = JwtUtils.generate(userId = "TEST_USER_ID")

        When("만약, ") {
            mockMvc.get("/auth/token") {
//                param("token", token)
            }.andDo { print() }.andReturn()

            Then("그러면,") {

            }
        }
    }
})