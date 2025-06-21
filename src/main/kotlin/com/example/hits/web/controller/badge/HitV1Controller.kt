package com.example.hits.web.controller.badge

import com.example.hits.service.HitService
import com.example.hits.util.MEDIA_TYPE_SVG
import com.example.hits.web.api.API_V1
import com.example.hits.web.controller.badge.util.svgResponse
import com.example.hits.web.util.SvgBadgeGenerator
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(API_V1)
class HitV1Controller(
    private val hitService: HitService
) {
    companion object {
        private val logger = LoggerFactory.getLogger(HitV1Controller::class.java)
    }

    @GetMapping("/badge", produces = [MEDIA_TYPE_SVG])
    fun incrementAndGetBadge(
        @RequestParam url: String,
        @RequestParam(required = false, defaultValue = "blue") color: String,
        @RequestParam(required = false, defaultValue = "zap") icon: String
    ): ResponseEntity<String> {
        val error = hitService.validateParams(url, "", color, icon)
        if(error != null) {
            return error
        }

        val count = hitService.increment(url)

        val title = url.substringAfterLast('/')

        logger.info("$title: $count")

        val svg = SvgBadgeGenerator.generateV1(title, count, color, icon)

        return svgResponse(svg)
    }
}