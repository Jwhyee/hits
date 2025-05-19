package com.example.hits.web.scheduler

import com.example.hits.domain.repository.HitRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class HitResetScheduler(
    private val hitRepository: HitRepository
) {

    @Scheduled(cron = "0 0 0 * * *")
    fun resetCounterDaily() {
        hitRepository.resetAll()
        println("[SCHEDULED] counter 초기화 완료: ${LocalDateTime.now()}")
    }
}