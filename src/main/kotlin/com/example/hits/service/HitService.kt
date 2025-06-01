package com.example.hits.service

import com.example.hits.domain.repository.HitRepository
import com.example.hits.web.controller.badge.HitV2Controller
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class HitService(
    private val repository: HitRepository
) {
    fun increment(url: String): Int {
        return repository.increment(url)
    }

    fun getRecentCounts(url: String): Map<LocalDate, Int> {
        return repository.getRecentCounts(url)
    }

    fun validateParams(
        url: String,
        title: String,
        color: String,
        icon: String
    ): ResponseEntity<String>? {
        if (url.length > 60) {
            return errorSvg("url must be ≤ 60 characters")
        }

        if (title.length > 30) {
            return errorSvg("title must be ≤ 30 characters")
        }

        if (!(color.length == 3 || color.length == 6)) {
            return errorSvg("color must be 3 or 6 characters")
        }

        if (icon.length > 5) {
            return errorSvg("icon must be ≤ 5 characters")
        }

        return null
    }

    private fun errorSvg(message: String): ResponseEntity<String> {
        val svg = """
        <svg xmlns="http://www.w3.org/2000/svg" width="360" height="20">
            <text x="0" y="15" fill="red">Error: $message</text>
        </svg>
    """.trimIndent()

        return ResponseEntity.badRequest()
            .contentType(MediaType.parseMediaType(HitV2Controller.MEDIA_TYPE_SVG))
            .body(svg)
    }
}