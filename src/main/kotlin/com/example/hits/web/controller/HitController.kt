package com.example.hits.web.controller

import com.example.hits.service.HitService
import com.example.hits.web.util.SvgBadgeGenerator
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/count")
class HitController(
    private val hitService: HitService
) {

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

        val svg = SvgBadgeGenerator.generate(title, count, color, icon)

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(MEDIA_TYPE_SVG)).body(svg)
    }
}