package com.example.hits

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HitsApplication

fun main(args: Array<String>) {
    runApplication<HitsApplication>(*args)
}
