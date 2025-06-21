package com.example.hits.domain.repository

import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

@Repository
class HitRepository {
    companion object {
        // 오늘로부터 2일 전까지의 데이터만 유지
        const val DATA_RETENTION_DAYS = 2L
    }

    private val counter = ConcurrentHashMap<String, MutableMap<LocalDate, AtomicInteger>>()

    fun increment(url: String): Int {
        val today = LocalDate.now()
        val dailyMap = counter.computeIfAbsent(url) { mutableMapOf() }

        // 3일 이전 기록 제거
        val cutoff = today.minusDays(DATA_RETENTION_DAYS)
        dailyMap.entries.removeIf { it.key.isBefore(cutoff) }

        val todayCounter = dailyMap.computeIfAbsent(today) { AtomicInteger(0) }
        return todayCounter.incrementAndGet()
    }

    fun getTodayCount(url: String): Int {
        val today = LocalDate.now()
        return counter[url]?.get(today)?.get() ?: 0
    }

    fun getRecentCounts(url: String): Map<LocalDate, Int> {
        val today = LocalDate.now()
        val dailyMap = counter[url] ?: return emptyMap()

        val cutoff = today.minusDays(2)
        dailyMap.entries.removeIf { it.key.isBefore(cutoff) }

        return (0L..2L).associate { offset ->
            val date = today.minusDays(offset)
            date to (dailyMap[date]?.get() ?: 0)
        }
    }

    fun resetAll() {
        counter.clear()
    }
}