package com.example.hits

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class HitsApplication

fun main(args: Array<String>) {
    runApplication<HitsApplication>(*args)
}
