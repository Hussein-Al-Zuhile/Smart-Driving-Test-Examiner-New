package com.tatweer.smartdrivingtest.presentation.driveTest.runningTest

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel
import kotlinx.coroutines.channels.TickerMode
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch

@Suppress("OPT_IN_USAGE")
class RunningTestViewModel : BaseViewModel<RunningTestScreenStateEvent>() {

    var state: RunningTestScreenState by mutableStateOf(RunningTestScreenState())
        private set

    fun onEvent(event: RunningTestScreenEvent) = when (event) {
        is RunningTestScreenEvent.OnRejectFault -> {
            state = state.copy(detectedFaults = state.detectedFaults.filter { it != event.fault })
        }

        is RunningTestScreenEvent.OnAcceptFault -> {
            state = state.copy(detectedFaults = state.detectedFaults.filter { it != event.fault })
        }

        RunningTestScreenEvent.OnAddManualFault -> {
            state = state.copy(isTestStarted = false)
            _singleStateEventChannel.sendInViewModelScope(RunningTestScreenStateEvent.NavigateToAddManualFaultDialog)
        }

        RunningTestScreenEvent.OnStartTest -> {
            startTimer()
            state = state.copy(isTestStarted = true)
        }

        RunningTestScreenEvent.OnStopTest -> {
        }

        RunningTestScreenEvent.OnEmergencyStop -> {
            _singleStateEventChannel.sendInViewModelScope(RunningTestScreenStateEvent.NavigateToEmergencyStop)
        }
    }

    fun startTimer() {
        viewModelScope.launch {
            ticker(
                1000,
                context = viewModelScope.coroutineContext,
                mode = TickerMode.FIXED_DELAY
            ).consumeEach {
                val passedTime = state.minutes * 60 + state.seconds
                val newTime = passedTime + 1
                val minutes = newTime / 60
                val seconds = newTime % 60
                state = state.copy(minutes = minutes, seconds = seconds)
            }
        }
    }

}