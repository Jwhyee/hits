package com.example.hits.service

import com.example.hits.util.MEDIA_TYPE_SVG
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

object Validation {
    fun params(
        url: String,
        title: String,
        color: String,
        icon: String
    ): ResponseEntity<String>? =
        validateUrl(url)
            ?: validateTitle(title)
            ?: validateColor(color)
            ?: validateIcon(icon)

    private fun validateUrl(url: String): ResponseEntity<String>? =
        if (url.length > 60) errorSvg("url must be ≤ 60 characters") else null

    private fun validateTitle(title: String): ResponseEntity<String>? =
        if (title.length > 30) errorSvg("title must be ≤ 30 characters") else null

    private fun validateColor(color: String): ResponseEntity<String>? =
        if (!(color.length == 3 || color.length == 6)) errorSvg("color must be 3 or 6 characters") else null

    private fun validateIcon(icon: String): ResponseEntity<String>? =
        if (icon.length > 5) errorSvg("icon must be ≤ 5 characters") else null

    private fun errorSvg(message: String): ResponseEntity<String> {
        val svg = """
        <svg xmlns="http://www.w3.org/2000/svg" width="360" height="20">
            <text x="0" y="15" fill="red">Error: $message</text>
        </svg>
    """.trimIndent()

        return ResponseEntity.badRequest()
            .contentType(MediaType.parseMediaType(MEDIA_TYPE_SVG))
            .body(svg)
    }
}