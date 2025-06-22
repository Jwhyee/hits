package com.example.hits.controller

import com.example.hits.service.HitService
import com.example.hits.service.ParamValidation
import com.example.hits.web.api.API_V2
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.hamcrest.Matchers.containsString
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig::class)
class HitControllerV2Test : BehaviorSpec() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var hitService: HitService

    @Autowired
    private lateinit var paramValidation: ParamValidation

    init {
        extension(SpringExtension)

        val testUrl = "https://github.com/test/repo"

        val today = LocalDate.now()
        val yesterday = today.minusDays(1)
        val twoDaysAgo = today.minusDays(2)

        beforeSpec {
            every {
                hitService.getRecentCounts(testUrl)
            } returns mapOf(
                today to 100,
                yesterday to 80,
                twoDaysAgo to 60
            )
            every {
                paramValidation.check(
                    eq("https://github.com/test/repo"),
                    any(),
                    any(),
                    any()
                )
            } returns null
        }

        given("GET $API_V2/badge") {
            `when`("called with full parameters") {
                then("should return a valid SVG with counts") {
                    mockMvc.perform(
                        get("$API_V2/badge")
                            .param("url", testUrl)
                            .param("title", "Visitors")
                            .param("icon", "zap")
                            .param("color", "4caf50")
                    )
                    .andExpect(status().isOk)
                    .andExpect(content().contentType("image/svg+xml"))
                    .andExpect(content().string(containsString(today.toString())))
                    .andExpect(content().string(containsString(yesterday.toString())))
                    .andExpect(content().string(containsString(twoDaysAgo.toString())))
                    .andExpect(content().string(containsString("100")))
                    .andExpect(content().string(containsString("80")))
                    .andExpect(content().string(containsString("60")))
                }
            }
        }
    }
}