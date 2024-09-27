package com.tatweer.smartdrivingtest.data.repository

import com.tatweer.smartdrivingtest.data.base.BaseRepository
import com.tatweer.smartdrivingtest.data.datasource.remote.http.MainService
import com.tatweer.smartdrivingtest.data.datasource.remote.mqtt.BackOfficeMQTTClient
import com.tatweer.smartdrivingtest.domain.model.Location


class MainRepository(
    private val mainService: MainService,
    private val backOfficeMQTTClient: BackOfficeMQTTClient
) : BaseRepository() {

    suspend fun publishLanguage(language: String) = publishToTopic {
        backOfficeMQTTClient.publishLanguage(language)
    }

    suspend fun publishLanguageBasedOnNationality(language: String) = publishToTopic {
        backOfficeMQTTClient.publishLanguageBasedOnNationality(language)
    }

    suspend fun publishReadingEID() = publishToTopic {
        backOfficeMQTTClient.publishReadingEID()
    }

    suspend fun publishCarLocation(location: Location) = publishToTopic {
        backOfficeMQTTClient.publishCarLocation(location)
    }

    suspend fun publishVoiceCommand(command: String) = publishToTopic {
        backOfficeMQTTClient.publishVoiceCommand(command)
    }

    suspend fun publishCarOutOfRoute(location: Location) = publishToTopic {
        backOfficeMQTTClient.publishCarOutOfRoute(location)
    }

}