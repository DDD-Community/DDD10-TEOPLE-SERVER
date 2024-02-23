package com.ddd.teople.bootstrap.api.map

import com.ddd.teople.bootstrap.BootstrapApplicationTest
import io.kotest.core.spec.style.BehaviorSpec
import org.slf4j.LoggerFactory
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.util.LinkedMultiValueMap

@SpringBootTest(classes = [BootstrapApplicationTest::class])
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MapControllerTest(
    private val mockMvc: MockMvc,
): BehaviorSpec({
    val log = LoggerFactory.getLogger(this::class.java)

    Given("먼저, 등록 성공") {
        val params = LinkedMultiValueMap<String, String>().apply {
            add("mapId", "TEOPLE_TEST99")
            add("lat", "1111")
            add("lng", "2222")
        }
        val token = "TEOPLE_TEST"

        When("만약,") {
            mockMvc.perform(
                MockMvcRequestBuilders.post("/map")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer $token")
                    .params(params)
            ).andDo(MockMvcResultHandlers.print()).andReturn()
        }
    }

    Given("먼저, 삭제 성공") {
        val params = LinkedMultiValueMap<String, String>().apply {
            add("mapId", "0dd48f7b3c2e4be195f0")
        }
        val token = "TEOPLE_TEST"

        When("만약,") {
            mockMvc.perform(
                MockMvcRequestBuilders.delete("/map")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer $token")
                    .params(params)
            ).andDo(MockMvcResultHandlers.print()).andReturn()
        }
    }

    Given("먼저, 조회 성공") {
        val token = "TEOPLE_TEST"

        When("만약,") {
            mockMvc.perform(
                MockMvcRequestBuilders.get("/map")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer $token")
            ).andDo(MockMvcResultHandlers.print()).andReturn()
        }
    }
})