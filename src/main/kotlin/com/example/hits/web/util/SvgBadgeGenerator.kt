package com.example.hits.web.util

object SvgBadgeGenerator {
    fun generate(title: String, count: Int, color: String): String {
        return """
            <svg xmlns="http://www.w3.org/2000/svg" width="200" height="20">
              <rect width="80" height="20" fill="#555"/>
              <rect x="80" width="120" height="20" fill="$color"/>
              <text x="10" y="14" fill="#fff" font-family="Verdana" font-size="11">$title</text>
              <text x="90" y="14" fill="#fff" font-family="Verdana" font-size="11">$count</text>
            </svg>
        """.trimIndent()
    }
}