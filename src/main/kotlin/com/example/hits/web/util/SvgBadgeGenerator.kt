package com.example.hits.web.util

object SvgBadgeGenerator {
    fun generate(title: String, count: Int, color: String): String {
        //language=SVG
        return """
            <svg xmlns="http://www.w3.org/2000/svg" width="200" height="20">
              <defs>
                <!-- 그라데이션 -->
                <linearGradient id="smooth" x2="0" y2="100%">
                  <stop offset="0" stop-color="#bbb" stop-opacity=".1"/>
                  <stop offset="1" stop-opacity=".1"/>
                </linearGradient>
            
                <!-- 둥근 마스크 -->
                <mask id="round">
                  <rect width="200" height="20" rx="3" fill="#fff"/>
                </mask>
            
                <!-- 텍스트 페이드 인 -->
                <style>
                  .fade-in {
                    animation: fadeIn 0.8s ease-in forwards;
                    opacity: 0;
                  }
            
                  .jump {
                    animation: jump 1s ease-in-out infinite;
                  }
            
                  @keyframes fadeIn {
                    to { opacity: 1; }
                  }
            
                  @keyframes jump {
                    0%, 100% { transform: translateY(0); }
                    50% { transform: translateY(-1px); }
                  }
                </style>
              </defs>
            
              <!-- 뱃지 배경 -->
              <g mask="url(#round)">
                <rect width="80" height="20" fill="#555"/>
                <rect x="80" width="120" height="20" fill="$color"/>
                <rect width="200" height="20" fill="url(#smooth)"/>
              </g>
            
              <!-- GitHub 아이콘 -->
              <g transform="translate(4, 2)">
                <path fill="#fff" d="M8 0C3.58 0 0 3.58 0 8a8 8 0 005.47 7.59c.4.07.55-.17.55-.38v-1.33c-2.23.49-2.7-1.07-2.7-1.07-.36-.92-.88-1.17-.88-1.17-.72-.5.05-.49.05-.49.8.06 1.22.82 1.22.82.71 1.22 1.87.87 2.33.66.07-.52.28-.87.5-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.01.08-2.11 0 0 .67-.21 2.2.82a7.54 7.54 0 012 0c1.53-1.03 2.2-.82 2.2-.82.44 1.1.16 1.91.08 2.11.51.56.82 1.28.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48v2.2c0 .21.15.46.55.38A8 8 0 0016 8c0-4.42-3.58-8-8-8z"/>
              </g>
            
              <!-- 텍스트 -->
              <g fill="#fff" font-family="Verdana" font-size="11">
                <text x="22" y="14" class="fade-in">$title</text>
                <text x="90" y="14" class="jump">$count</text>
              </g>
            </svg>
        """.trimIndent()
    }
}