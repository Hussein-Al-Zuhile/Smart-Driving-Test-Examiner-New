package com.tatweer.smartdrivingtest.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn

abstract class BaseViewModel<StateEventType: StateEvent> : ViewModel() {

    protected suspend fun <T> Flow<T>.asStateFlowInViewModelScope() = stateIn(viewModelScope)

    /**
     * State Events are a one-time event that is handled only once (Navigation, Toast, SnackBar, etc.)
     * and it's sent by the View Model, and consumed by the UI.
     */
    protected val _singleStateEventChannel = Channel<StateEventType>()
    val singleStateEventChannel: ReceiveChannel<StateEventType> = _singleStateEventChannel

}