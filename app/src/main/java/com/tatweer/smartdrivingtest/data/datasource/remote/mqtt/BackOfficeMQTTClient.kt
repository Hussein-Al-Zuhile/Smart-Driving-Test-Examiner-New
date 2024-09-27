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

    fun publishLanguage(language: String) =
        publishWithDefaults(Event.SetLanguage.topic, language.toByteArray())

    fun publishLanguageBasedOnNationality(language: String) =
        publishWithDefaults(Event.SetLanguageBasedOnNationality.topic, language.toByteArray())

    fun publishReadingEID() =
        publishWithDefaults(Event.ReadEIDInProgress.topic, "Reading EID In Progress".toByteArray())

    fun publishCarLocation(location: Location) =
        publishWithDefaults(Event.CarLocation.topic, Json.encodeToString(location).toByteArray())

    fun publishVoiceCommand(command: String) =
        publishWithDefaults(Event.RoutingCommand.topic, command.toByteArray())

    fun publishCarOutOfRoute(location: Location) =
        publishWithDefaults(Event.CarOutOfRout.topic, Json.encodeToString(location).toByteArray())


}

private enum class Event(val topic: String) {
    FaultDetected("FaultDetected"),
    EmergencyStop("EmergencyStop"),
    BoardAssigned("BoardAssigned"),
    CarLocation("SetLocation"),
    CarOutOfRout("CarOutOfRout"),
    VideoSyncing("VideoSyncing"),
    ReceiveListOfAbsents("ReceiveListOfAbsents"),
    SetLanguage("SetLanguage"),
    SetLanguageBasedOnNationality("SetLanguageNationality"),
    HardwareFailed("HardwareFailed"),
    ReadEIDInProgress("ReadEIDA"),
    ReadEIDADone("ReadEIDADone"),
    RoutingCommand("RoutingCommand"),
    RoutingCommandResponse("RoutingCommandResponse"),
    ReceiveLocation("ReceiveLocation")
}
