package com.example.hits.controller

import com.example.hits.service.HitService
import com.example.hits.web.api.API_V2
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.mockk.every
import io.mockk.mockk
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
@Import(HitControllerV2Test.TestConfig::class)
class HitControllerV2Test : BehaviorSpec() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var hitService: HitService

    init {
        extension(SpringExtension)

        val testUrl = "https://github.com/test/repo"

        val today = LocalDate.now()
        val yesterday = today.minusDays(1)
        val twoDaysAgo = today.minusDays(2)

        beforeSpec {
            every { hitService.getRecentCounts(testUrl) } returns mapOf(
                today to 100,
                yesterday to 80,
                twoDaysAgo to 60
            )
            every { hitService.validateParams("https://github.com/test/repo", any(), any(), any()) } returns null
        }

        given("GET $API_V2/badge") {
            `when`("called with full parameters") {
                then("should return a valid SVG with counts") {
                    val result = mockMvc.perform(
                        get("$API_V2/badge")
                            .param("url", testUrl)
                            .param("title", "Visitors")
                            .param("icon", "zap")
                            .param("color", "4caf50")
                    ).andReturn().response

                    println("SVG 응답: ${result.contentAsString}")

                    result.status shouldBe 200
                    result.contentType shouldBe "image/svg+xml"
                    result.contentAsString shouldContain "<svg"
                    result.contentAsString shouldContain "Visitors"
                    result.contentAsString shouldContain "100"
                    result.contentAsString shouldContain "80"
                    result.contentAsString shouldContain "60"
                }
            }
        }
    }

    @TestConfiguration
    class TestConfig {
        @Bean
        fun hitService(): HitService = mockk(relaxed = true)
    }
}