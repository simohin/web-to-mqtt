package ru.simohin.webtomqtt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebToMqttApplication

fun main(args: Array<String>) {
    runApplication<WebToMqttApplication>(*args)
}
