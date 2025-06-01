package com.example.hits.web.util

object SvgBadgeGenerator {
    private val EMOJI_MAP = mapOf(
        "zap" to "⚡",
        "fire" to "🔥",
        "heart" to "❤️",
        "star" to "⭐"
    )

    fun generateV1(title: String, count: Int, color: String, icon: String): String {
        val titleWidth = (8 * title.length + 36).coerceAtLeast(60)     // 최소 폭 보장
        val countStr = count.toString()
        val countWidth = (10 * countStr.length + 20).coerceAtLeast(40)
        val totalWidth = titleWidth + countWidth

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

    fun generateV2(title: String, count: Int, color: String, icon: String): String {
        val emojiChar = EMOJI_MAP[icon] ?: EMOJI_MAP["zap"]

        val titleWidth = (8 * title.length + 36).coerceAtLeast(60)
        val countStr = count.toString()
        val countWidth = (10 * countStr.length + 20).coerceAtLeast(40)
        val totalWidth = titleWidth + countWidth

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

              <!-- 아이콘 + 타이틀 -->
              <text x="8" y="19" fill="#fff" font-family="Segoe UI, sans-serif" font-size="13">
                $emojiChar   $title
              </text>

              <!-- 숫자 -->
              <text x="${titleWidth + 10}" y="19" fill="#fff" font-family="Segoe UI, sans-serif" font-size="13">
                $count
              </text>
            </svg>
        """.trimIndent()
    }
}