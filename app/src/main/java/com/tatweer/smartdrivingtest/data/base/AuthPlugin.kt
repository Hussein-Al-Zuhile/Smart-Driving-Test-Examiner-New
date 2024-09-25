package com.tatweer.smartdrivingtest.data.base

import io.ktor.client.plugins.api.Send
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.http.HttpStatusCode
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
val AuthPlugin = createClientPlugin("AuthPlugin", ::AuthPluginConfig) {
    val token = pluginConfig.token

    on(Send) { request ->
        val originalCall = proceed(request)
        originalCall.response.run {
            if(status == HttpStatusCode.Unauthorized && headers["WWW-Authenticate"]?.contains("Bearer") == true) {
                request.headers.append("Authorization", "Bearer $token")
                proceed(request)
            } else {
                originalCall
            }
        }
    }
}

class AuthPluginConfig {
    var token: String = ""
}