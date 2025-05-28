package com.example.hits.web.util

object SvgBadgeGenerator {
    private val TWEMOJI_SVGS = mapOf(
        "fire" to "1f525", "heart" to "2764", "star" to "2b50", "zap" to "26a1"
    )

    fun generate(title: String, count: Int, color: String, icon: String): String {
        val iconCode = TWEMOJI_SVGS[icon] ?: TWEMOJI_SVGS["zap"]
        val iconUrl = "https://cdnjs.cloudflare.com/ajax/libs/twemoji/14.0.2/svg/$iconCode.svg"

        // 동적 크기 계산
        val titleWidth = (8 * title.length + 36).coerceAtLeast(60)     // 최소 폭 보장
        val countStr = count.toString()
        val countWidth = (10 * countStr.length + 20).coerceAtLeast(40)
        val totalWidth = titleWidth + countWidth

        // language=svg
        return """
            <svg xmlns="http://www.w3.org/2000/svg" width="$totalWidth" height="28" role="img" aria-label="$title: $count">
              <defs>
                <linearGradient id="smooth" x2="0" y2="100%">
                  <stop offset="0" stop-color="#bbb" stop-opacity=".2"/>
                  <stop offset="1" stop-opacity=".1"/>
                </linearGradient>
                <clipPath id="round">
                  <rect width="$totalWidth" height="28" rx="6" ry="6"/>
                </clipPath>
              </defs>

              <g clip-path="url(#round)">
                <rect width="$titleWidth" height="28" fill="#555"/>
                <rect x="$titleWidth" width="$countWidth" height="28" fill="#$color"/>
                <rect width="$totalWidth" height="28" fill="url(#smooth)"/>
              </g>

              <!-- 아이콘 텍스트 버전 -->
              <text x="8" y="19" fill="#fff" font-family="Segoe UI, sans-serif" font-size="13">⚡</text>

              <!-- 텍스트 -->
              <g fill="#fff" font-family="Segoe UI, sans-serif" font-size="13">
                <text x="26" y="19">$title</text>
                <text x="${titleWidth + 10}" y="19">$count</text>
              </g>
            </svg>
        """.trimIndent()
    }
}