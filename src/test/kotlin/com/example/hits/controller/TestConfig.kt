package com.example.hits.controller

import com.example.hits.service.HitService
import com.example.hits.service.ParamValidation
import io.mockk.mockk
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestConfig {
    @Bean
    fun hitService(): HitService = mockk(relaxed = true)

    @Bean
    fun paramValidation(): ParamValidation = mockk(relaxed = true)
}