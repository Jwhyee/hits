package com.example.hits.web.util

import java.time.LocalDate

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

    fun generateV2(
        title: String,
        icon: String,
        color: String,
        counts: Map<LocalDate, Int>
    ): String {
        val emojiChar = EMOJI_MAP[icon] ?: EMOJI_MAP["zap"]
        val today = LocalDate.now()
        val days = listOf(0L, 1L, 2L).map { today.minusDays(it) }
        val values = days.map { counts[it] ?: 0 }

        val dateStrings = days.mapIndexed { index, date ->
            val label = when (index) {
                0 -> "Today"
                1 -> "Yesterday"
                2 -> "2 days ago"
                else -> ""
            }
            "${date} ($label)"
        }

        return """
        <svg xmlns="http://www.w3.org/2000/svg" width="360" height="220" version="1.1">
          <style>
            <![CDATA[
              @keyframes fadeIn {
                from { opacity: 0 } to { opacity: 1 }
              }
              text {
                fill: white;
                font-family: 'Segoe UI', sans-serif;
              }
              .title {
                font-size: 1.3em;
                font-weight: 700;
                animation: fadeIn 1s ease-in-out forwards;
              }
              .date {
                font-size: 0.9em;
                font-weight: 500;
              }
              .count {
                font-size: 0.9em;
                font-weight: 400;
                text-anchor: end;
              }
              .bar-bg {
                stroke: white;
                stroke-opacity: 0.3;
                stroke-width: 6;
                stroke-linecap: round;
              }
              .bar-fg {
                stroke: white;
                stroke-opacity: 0.9;
                stroke-width: 6;
                stroke-linecap: round;
              }
              .fadeIn {
                opacity: 0;
                animation: fadeIn 1s ease-out forwards;
              }
            ]]>
          </style>

          <defs>
            <linearGradient id="grad" x1="0%" y1="0%" x2="100%" y2="35%">
              <stop offset="0%" stop-color="#$color" stop-opacity="0.4"/>
              <stop offset="100%" stop-color="#$color" stop-opacity="1"/>
            </linearGradient>
          </defs>

          <rect width="360" height="220" rx="12" ry="12" fill="url(#grad)" />

          <text x="28" y="40" class="title">$emojiChar  $title</text>

          <g class="fadeIn" style="animation-delay:0.3s">
            <text x="28" y="80" class="date">${dateStrings[0]}</text>
            <text x="330" y="80" class="count">${values[0]}</text>
            <line x1="28" y1="96" x2="330" y2="96" class="bar-bg" />
            <line x1="28" y1="96" x2="${28 + (values[0] * 3).coerceAtMost(300)}" y2="96" class="bar-fg" />
          </g>

          <g class="fadeIn" style="animation-delay:0.5s">
            <text x="28" y="128" class="date">${dateStrings[1]}</text>
            <text x="330" y="128" class="count">${values[1]}</text>
            <line x1="28" y1="144" x2="330" y2="144" class="bar-bg" />
            <line x1="28" y1="144" x2="${28 + (values[1] * 3).coerceAtMost(300)}" y2="144" class="bar-fg" />
          </g>

          <g class="fadeIn" style="animation-delay:0.7s">
            <text x="28" y="176" class="date">${dateStrings[2]}</text>
            <text x="330" y="176" class="count">${values[2]}</text>
            <line x1="28" y1="192" x2="330" y2="192" class="bar-bg" />
            <line x1="28" y1="192" x2="${28 + (values[2] * 3).coerceAtMost(300)}" y2="192" class="bar-fg" />
          </g>
        </svg>
    """.trimIndent()
    }
}