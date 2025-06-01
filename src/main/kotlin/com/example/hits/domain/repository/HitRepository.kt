package com.example.hits.domain.repository

import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

@Repository
class HitRepository {

    private val counter = ConcurrentHashMap<String, MutableMap<LocalDate, AtomicInteger>>()

    fun increment(url: String): Int {
        val today = LocalDate.now()
        val dailyMap = counter.computeIfAbsent(url) { mutableMapOf() }

        // 3일 이전 기록 제거
        val cutoff = today.minusDays(2)
        dailyMap.entries.removeIf { it.key.isBefore(cutoff) }

        val todayCounter = dailyMap.computeIfAbsent(today) { AtomicInteger(0) }
        return todayCounter.incrementAndGet()
    }

    fun getTodayCount(url: String): Int {
        val today = LocalDate.now()
        return counter[url]?.get(today)?.get() ?: 0
    }

    fun getRecentCounts(url: String): Map<LocalDate, Int> {
        increment(url)

        val today = LocalDate.now()
        val dailyMap = counter[url] ?: return emptyMap()

        return (0L..2L).associate { offset ->
            val date = today.minusDays(offset)
            date to (dailyMap[date]?.get() ?: 0)
        }
    }

    fun resetAll() {
        counter.clear()
    }
}