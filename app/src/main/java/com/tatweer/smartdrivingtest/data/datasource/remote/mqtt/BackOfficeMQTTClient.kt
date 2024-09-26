package com.tatweer.smartdrivingtest.data.datasource.remote.mqtt

import com.hivemq.client.mqtt.mqtt3.Mqtt3Client
import com.tatweer.smartdrivingtest.data.base.BaseMQTTClient
import com.tatweer.smartdrivingtest.domain.model.Location
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.UUID

class BackOfficeMQTTClient : BaseMQTTClient() {

    override val client = Mqtt3Client.builder()
        .identifier(UUID.randomUUID().toString())
        .serverHost(MQTT_SERVER_URI)
        .serverPort(MQTT_SERVER_PORT)
        .buildAsync()

    private companion object {
        const val MQTT_SERVER_URI = "tcp://192.168.9.21"
        const val MQTT_SERVER_PORT = 1883
    }

}