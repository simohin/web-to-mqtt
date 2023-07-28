package ru.simohin.webtomqtt

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("publish")
class PublishController(
    private val wqttService: WqttService
) {

    @GetMapping
    fun publish(@RequestParam topic: String, @RequestParam message: String) = wqttService.publish(topic, message)
}
