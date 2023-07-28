package ru.simohin.webtomqtt

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("publish")
class PublishController(
    private val wqttService: WqttService
) {

    @PostMapping
    fun publish(@RequestBody payload: PublishRequestDto) = wqttService.publish(payload.topic, payload.message)
}

data class PublishRequestDto(
    val topic: String,
    val message: String
)
