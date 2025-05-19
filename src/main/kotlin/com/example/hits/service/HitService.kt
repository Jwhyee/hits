package com.example.hits.service

import com.example.hits.domain.repository.HitRepository
import org.springframework.stereotype.Service

@Service
class HitService(
    private val repository: HitRepository
) {
    fun increment(url: String): Int {
        return repository.increment(url)
    }
}