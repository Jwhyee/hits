package com.example.hits.web.controller.badge.util

import com.example.hits.util.MEDIA_TYPE_SVG
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

fun svgResponse(body: String): ResponseEntity<String> =
    ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(MEDIA_TYPE_SVG))
        .header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
        .header(HttpHeaders.PRAGMA, "no-cache")
        .header(HttpHeaders.EXPIRES, "0")
        .body(body)