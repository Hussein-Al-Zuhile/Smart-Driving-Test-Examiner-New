package com.tatweer.smartdrivingtest.presentation.driveTest.runningTest

import com.tatweer.smartdrivingtest.domain.model.Fault

sealed interface RunningTestScreenEvent {
    data class OnRejectFault(val fault: Fault) : RunningTestScreenEvent
    data class OnAcceptFault(val fault: Fault) : RunningTestScreenEvent
    data object OnAddManualFault : RunningTestScreenEvent
    data object OnStartTest : RunningTestScreenEvent
    data object OnStopTest : RunningTestScreenEvent
    data object OnEmergencyStop : RunningTestScreenEvent
}