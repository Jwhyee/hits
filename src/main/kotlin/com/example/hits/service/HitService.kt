package com.example.hits.service

import com.example.hits.domain.repository.HitRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class HitService(
    private val repository: HitRepository
) {
    fun increment(url: String): Int {
        return repository.increment(url)
    }

    fun getRecentCounts(url: String): Map<LocalDate, Int> {
        repository.increment(url)
        return repository.getRecentCounts(url)
    }
}