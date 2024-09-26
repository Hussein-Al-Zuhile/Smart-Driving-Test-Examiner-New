package com.tatweer.smartdrivingtest.data.base

import com.hivemq.client.internal.mqtt.message.publish.puback.MqttPubAck
import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish
import com.hivemq.client.mqtt.mqtt3.message.publish.puback.Mqtt3PubAck
import com.hivemq.client.mqtt.mqtt3.message.subscribe.suback.Mqtt3SubAck
import com.tatweer.smartdrivingtest.domain.base.Result
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.future.await
import java.util.concurrent.CompletableFuture

abstract class BaseRepository() {

    suspend inline fun <reified T> sendRemoteRequest(request: (() -> HttpResponse)): Result<T> {
        val result = try {
            Result.success(request().body<T>())
        } catch (exception: ClientRequestException) { // for 4xx responses
            exception.printStackTrace()
            if (exception.response.status == HttpStatusCode.Unauthorized) {
                Result.Failure.Unauthorized(message = exception.message)
            } else {
                Result.Failure.BadRequestException(message = exception.message)
            }
        } catch (exception: ServerResponseException) {
            exception.printStackTrace()
            Result.Failure.ServerErrorException(message = exception.message)
        } catch (exception: Exception) {
            exception.printStackTrace()
            if (exception is CancellationException) throw exception

            Result.failure(message = exception.message)
        }
        return result
    }

    suspend inline fun subscribeToTopic(request: () -> CompletableFuture<Mqtt3SubAck>): Result<Unit> {
        val result = try {
            val response = request().await()

            if (response.returnCodes.any { it.isError }) {
                Result.Failure.MQTT.SubscriptionFailed()
            } else {
                Result.success<Unit>()
            }
        } catch (exception: Exception) {
            exception.printStackTrace()

            Result.failure(message = exception.message)
        }

        return result
    }

    suspend inline fun publishToTopic(request: () -> CompletableFuture<Mqtt3Publish>): Result<Unit> {
        val result = try {
            request().await()

            Result.success<Unit>()
        } catch (exception: Exception) {
            exception.printStackTrace()

            Result.failure(message = exception.message)
        }

        return result
    }
}