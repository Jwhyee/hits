package com.example.hits.controller

import com.example.hits.service.HitService
import com.example.hits.service.ParamValidation
import com.example.hits.web.api.API_V1
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig::class)
class HitControllerV1Test : BehaviorSpec() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var hitService: HitService

    @Autowired
    private lateinit var paramValidation: ParamValidation

    init {
        extension(SpringExtension)

        beforeSpec {
            every { hitService.increment("https://github.com/test/repo") } returns 42
            every {
                paramValidation.check(
                    eq("https://github.com/test/repo"),
                    any(),
                    any(),
                    any()
                )
            } returns null
        }

        given("GET $API_V1") {
            `when`("called with valid url") {
                then("should return valid SVG") {
                    val result = mockMvc.perform(
                        get("$API_V1/badge")
                            .param("url", "https://github.com/test/repo")
                    ).andReturn().response

                    println("SVG 응답: ${result.contentAsString}")

                    result.status shouldBe 200
                    result.contentType shouldBe "image/svg+xml"
                    result.contentAsString shouldContain "<svg"
                    result.contentAsString shouldContain "42"
                }
            }
        }
    }
}