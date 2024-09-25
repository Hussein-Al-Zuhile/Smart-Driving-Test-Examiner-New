package com.tatweer.smartdrivingtest.data.base

import com.tatweer.smartdrivingtest.data.datasource.remote.http.ApiResources
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.get
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse

abstract class BaseRemoteService(protected val client: HttpClient) {

    // Helpers
    protected suspend inline fun <reified T : ApiResources> get(
        resource: T,
        builder: HttpRequestBuilder.() -> Unit = {}
    ): HttpResponse =
        client.get<T>(resource, builder)

    protected suspend inline fun post(
        resource: ApiResources,
        builder: HttpRequestBuilder.() -> Unit = {}
    ): HttpResponse =
        client.post(resource::class, builder)
}