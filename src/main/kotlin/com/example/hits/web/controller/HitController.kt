package com.example.hits.web.controller

import com.example.hits.service.HitService
import com.example.hits.web.api.API_ORIGIN
import com.example.hits.web.util.SvgBadgeGenerator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(API_ORIGIN)
class HitController(
    private val hitService: HitService
) {
    val logger = LoggerFactory.getLogger(HitController::class.java)

    companion object {
        const val MEDIA_TYPE_SVG = "image/svg+xml"
    }

    @GetMapping("/badge", produces = [MEDIA_TYPE_SVG])
    fun incrementAndGetBadge(
        @RequestParam url: String,
        @RequestParam(required = false, defaultValue = "blue") color: String,
        @RequestParam(required = false, defaultValue = "zap") icon: String
    ): ResponseEntity<String> {
        val count = hitService.increment(url)

        val title = url.substringAfterLast('/')

        logger.info("$title: $count")

        val svg = SvgBadgeGenerator.generate(title, count, color, icon)

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(MEDIA_TYPE_SVG))
            .header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
            .header(HttpHeaders.PRAGMA, "no-cache")
            .header(HttpHeaders.EXPIRES, "0")
            .body(svg)
    }
}