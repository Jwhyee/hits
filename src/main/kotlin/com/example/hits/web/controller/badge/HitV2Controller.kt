package com.example.hits.web.controller.badge

import com.example.hits.service.HitService
import com.example.hits.web.api.API_V2
import com.example.hits.web.util.SvgBadgeGenerator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(API_V2)
class HitV2Controller(
    private val hitService: HitService
) {
    val logger = LoggerFactory.getLogger(HitV2Controller::class.java)

    companion object {
        const val MEDIA_TYPE_SVG = "image/svg+xml"
    }

    @GetMapping("/badge", produces = [MEDIA_TYPE_SVG])
    fun incrementAndGetBadge(
        @RequestParam url: String,
        @RequestParam(required = false) title: String, // 사용자가 지정한 title
        @RequestParam(required = false, defaultValue = "blue") color: String,
        @RequestParam(required = false, defaultValue = "zap") icon: String
    ): ResponseEntity<String> {
        hitService.validateParams(url, title, color, icon)?.let {
            return it
        }

        val count = hitService.getRecentCounts(url)

        val resolvedTitle = title.takeIf { it.isNotBlank() } ?: url.substringAfterLast('/')

        logger.info("$resolvedTitle: $count")

        val svg = SvgBadgeGenerator.generateV2(resolvedTitle, icon, color, count)

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(MEDIA_TYPE_SVG))
            .header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
            .header(HttpHeaders.PRAGMA, "no-cache")
            .header(HttpHeaders.EXPIRES, "0")
            .body(svg)
    }
}