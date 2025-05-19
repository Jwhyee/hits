package com.example.hits.domain.repository

import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

@Repository
class HitRepository {
    private val counter = ConcurrentHashMap<String, AtomicInteger>()

    fun increment(url: String): Int {
        return counter.computeIfAbsent(url) { AtomicInteger(0) }.incrementAndGet()
    }

    fun resetAll() {
        counter.clear()
    }
}