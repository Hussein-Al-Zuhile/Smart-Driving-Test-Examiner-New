package com.tatweer.smartdrivingtest.data.repository

import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish
import com.tatweer.smartdrivingtest.data.base.BaseRepository
import com.tatweer.smartdrivingtest.data.datasource.remote.http.MainService
import com.tatweer.smartdrivingtest.data.datasource.remote.mqtt.InVehicleMQTTClient
import com.tatweer.smartdrivingtest.domain.model.Location
import kotlinx.coroutines.flow.Flow


class MainRepository(
    private val mainService: MainService,
    private val inVehicleMQTTClient: InVehicleMQTTClient
) : BaseRepository() {

    suspend fun publishLanguage(language: String) = publishToTopic {
        inVehicleMQTTClient.publishLanguage(language)
    }

    suspend fun publishLanguageBasedOnNationality(language: String) = publishToTopic {
        inVehicleMQTTClient.publishLanguageBasedOnNationality(language)
    }

    suspend fun publishReadingEID() = publishToTopic {
        inVehicleMQTTClient.publishReadingEID()
    }

    suspend fun publishCarLocation(location: Location) = publishToTopic {
        inVehicleMQTTClient.publishCarLocation(location)
    }

    suspend fun publishVoiceCommand(command: String) = publishToTopic {
        inVehicleMQTTClient.publishVoiceCommand(command)
    }

    suspend fun publishCarOutOfRoute(location: Location) = publishToTopic {
        inVehicleMQTTClient.publishCarOutOfRoute(location)
    }

}