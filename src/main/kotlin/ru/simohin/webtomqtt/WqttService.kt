package ru.simohin.webtomqtt

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*


@Service
class WqttService(
    @Value("\${mqtt.url:tcp://127.0.0.1:1883}")
    private val mqttUrl: String,
    @Value("\${mqtt.username:web-to-mqtt}")
    private val mqttUsername: String,
    @Value("\${mqtt.password:password}")
    private val mqttPassword: String,
) {

    private val clientId = UUID.randomUUID().toString()
    private val clientOptions = MqttConnectOptions().apply {

        userName = mqttUsername
        password = mqttPassword.toCharArray()
    }
    private val client = MqttClient(mqttUrl, clientId)

    @PostConstruct
    fun init() {
        client.connect(clientOptions)
    }

    @PreDestroy
    fun close() {
        client.close(true)
    }

    fun publish(topic: String, message: String): Mono<Void>  = Mono.fromRunnable {
        client.publish(topic, MqttMessage(message.toByteArray()))
    }
}
