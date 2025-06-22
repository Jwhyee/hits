package com.example.hits.domain.repository

import org.springframework.stereotype.Repository
import org.springframework.scheduling.annotation.Scheduled
import java.time.LocalDate
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

interface HitRepository {
    fun increment(url: String): Int
    fun getTodayCount(url: String): Int
    fun getRecentCounts(url: String): Map<LocalDate, Int>
    fun resetAll()
}

@Repository
class InMemoryHitRepository : HitRepository {
    companion object {
        // 오늘로부터 2일 전까지의 데이터만 유지
        const val DATA_RETENTION_DAYS = 2L
    }

    private val counter = ConcurrentHashMap<String, ConcurrentHashMap<Long, AtomicInteger>>()

    override fun increment(url: String): Int {
        val todayEpoch = LocalDate.now().toEpochDay()
        val dailyMap = counter.computeIfAbsent(url) { ConcurrentHashMap() }
        return dailyMap.computeIfAbsent(todayEpoch) { AtomicInteger(0) }.incrementAndGet()
    }

    override fun getTodayCount(url: String): Int {
        val todayEpoch = LocalDate.now().toEpochDay()
        return counter[url]?.get(todayEpoch)?.get() ?: 0
    }

    override fun getRecentCounts(url: String): Map<LocalDate, Int> {
        val todayEpoch = LocalDate.now().toEpochDay()
        val dailyMap = counter[url] ?: return emptyMap()
        return (0L..DATA_RETENTION_DAYS).associate { offset ->
            val epoch = todayEpoch - offset
            LocalDate.ofEpochDay(epoch) to (dailyMap[epoch]?.get() ?: 0)
        }
    }

    override fun resetAll() {
        counter.clear()
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "UTC")
    fun cleanup() {
        val cutoffEpoch = LocalDate.now().minusDays(DATA_RETENTION_DAYS).toEpochDay()
        counter.values.forEach { dailyMap ->
            dailyMap.entries.removeIf { it.key < cutoffEpoch }
        }
    }
}