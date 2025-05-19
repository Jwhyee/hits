package com.example.hits.controller

import com.example.hits.service.HitService
import com.example.hits.util.AutowireHelper
import com.example.hits.web.controller.HitController
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@Import(HitController::class)
@WebMvcTest(HitController::class)
class HitControllerTest : BehaviorSpec({

    val hitService = mockk<HitService>()

    val mockMvc = AutowireHelper.autoWireMockMvc(HitController(hitService))

    Given("GET /api/count/incr/badge.svg") {
        val testUrl = "https://github.com/test/repo"
        val expectedCount = 42

        every { hitService.increment(testUrl) } returns expectedCount

        When("valid request with default parameters is made") {
            val result = mockMvc.perform(
                get("/api/count/incr/badge.svg")
                    .param("url", testUrl)
            ).andReturn().response

            Then("it should return a valid SVG with status 200") {
                result.status shouldBe 200
                result.contentType shouldBe "image/svg+xml"
                result.contentAsString shouldContain expectedCount.toString()
                result.contentAsString shouldContain "<svg"
            }
        }
    }
})