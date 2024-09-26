package com.tatweer.smartdrivingtest.data.base

import com.hivemq.client.mqtt.MqttGlobalPublishFilter
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient
import com.hivemq.client.mqtt.mqtt3.message.connect.Mqtt3Connect
import com.hivemq.client.mqtt.mqtt3.message.connect.connack.Mqtt3ConnAck
import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish
import com.hivemq.client.mqtt.mqtt3.message.subscribe.suback.Mqtt3SubAck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.CompletableFuture


// TODO: Add check for connection before publishing,subscribing and listening.
abstract class BaseMQTTClient() {

    protected abstract val client: Mqtt3AsyncClient

    //region APIs
    fun connect(renewSession: Boolean = false): CompletableFuture<Mqtt3ConnAck> = client.connect(
        Mqtt3Connect.builder()
            .cleanSession(renewSession)
            .build()
    )

    fun disconnect() = client.disconnect()

    private lateinit var messagesFlow: Flow<Mqtt3Publish>

    fun singletonFlowOfMessages(): Flow<Mqtt3Publish> {
        if (!::messagesFlow.isInitialized) {
            messagesFlow = flowOfMessages()
        }

        return messagesFlow
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun flowOfMessages(): Flow<Mqtt3Publish> = callbackFlow {
        client.publishes(MqttGlobalPublishFilter.SUBSCRIBED) {
            trySend(it)
        }

        invokeOnClose {
            println("Listening ended")
        }
    }
    //endregion

    //region Helpers

    protected fun unsubscribeWithDefaults(topic: String) = client.unsubscribeWith()
        .topicFilter(topic)
        .send()

    protected fun subscribeWithDefaults(
        topic: String,
        qos: MqttQos = MqttQos.AT_LEAST_ONCE
    ): CompletableFuture<Mqtt3SubAck> =
        client.subscribeWith()
            .topicFilter(topic)
            .qos(qos)
            .send()

    protected fun publishWithDefaults(
        topic: String,
        payload: ByteArray,
        qos: MqttQos = MqttQos.AT_LEAST_ONCE
    ): CompletableFuture<Mqtt3Publish> = client.publishWith()
        .topic(topic)
        .qos(qos)
        .payload(payload)
        .send()
    //endregion
}