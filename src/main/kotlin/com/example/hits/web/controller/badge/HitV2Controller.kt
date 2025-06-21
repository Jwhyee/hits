package com.example.hits.web.controller.badge

import com.example.hits.service.HitService
import com.example.hits.service.Validation
import com.example.hits.util.MEDIA_TYPE_SVG
import com.example.hits.web.api.API_V2
import com.example.hits.web.controller.badge.util.svgResponse
import com.example.hits.web.util.SvgBadgeGenerator
import org.slf4j.LoggerFactory
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
    companion object {
        private val logger = LoggerFactory.getLogger(HitV2Controller::class.java)
    }

    @GetMapping("/badge", produces = [MEDIA_TYPE_SVG])
    fun incrementAndGetBadge(
        @RequestParam url: String,
        @RequestParam(required = false) title: String,
        @RequestParam(required = false, defaultValue = "blue") color: String,
        @RequestParam(required = false, defaultValue = "zap") icon: String
    ): ResponseEntity<String> {
        val error = Validation.params(url, title, color, icon)
        if(error != null) {
            return error
        }

        val count = hitService.getRecentCounts(url)

        val resolvedTitle = title.takeIf { it.isNotBlank() } ?: url.substringAfterLast('/')

        logger.info("$resolvedTitle: $count")

        val svg = SvgBadgeGenerator.generateV2(resolvedTitle, icon, color, count)

        return svgResponse(svg)
    }
}