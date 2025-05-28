package com.example.hits.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/count")
class GeneratorController {
    @GetMapping("/generate")
    fun generate(): String {
        return "generate"
    }
}