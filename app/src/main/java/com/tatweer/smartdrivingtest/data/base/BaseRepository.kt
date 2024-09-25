package com.tatweer.smartdrivingtest.data.base

import com.tatweer.smartdrivingtest.domain.base.Result
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CancellationException

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
}