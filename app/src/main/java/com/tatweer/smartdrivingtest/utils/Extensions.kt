package com.tatweer.smartdrivingtest.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach

@Composable
fun <T> ReceiveChannel<T>.consumeEach(onReceived: (T) -> Unit) {
    LaunchedEffect(this) {
        for (stateEvent in this@consumeEach) {
            onReceived(stateEvent)
        }
    }
}