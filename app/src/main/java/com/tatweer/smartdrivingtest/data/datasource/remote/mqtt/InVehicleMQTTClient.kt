package com.tatweer.smartdrivingtest.data.datasource.remote.mqtt

import org.eclipse.paho.client.mqttv3.MqttClient

class InVehicleMQTTClient {

    private val mqttClient: MqttClient by lazy { MqttClient(MQTT_SERVER_URI, MQTT_CLIENT_ID) }

    private companion object {

        const val MQTT_SERVER_URI = "tcp://broker.hivemq.com:1883"
        const val MQTT_CLIENT_ID = "SmartDrivingTestExaminer"

        const val ExamStart = "exam/start"
        const val ExamEnd = "exam/end"
        const val ValidateStudentBefore = "student/validation"
        const val StudentSelectedToStartTest =
            "student/selectedStudentToStartTest" // invoked when selected student
        const val TestSelectedToStart = "test/testSelectedToStart" // invoked when select test

    }

    fun connect() {
        mqttClient.connect()
    }

    fun disconnect() {
        mqttClient.disconnect()
    }

    fun startExam() {
        mqttClient
    }

}