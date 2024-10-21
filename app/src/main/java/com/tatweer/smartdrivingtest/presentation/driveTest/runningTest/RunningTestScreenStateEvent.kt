package com.tatweer.smartdrivingtest.presentation.driveTest.runningTest

import com.tatweer.smartdrivingtest.presentation.base.StateEvent

sealed interface RunningTestScreenStateEvent : StateEvent {
    data object NavigateToSummary : RunningTestScreenStateEvent
    data object NavigateToEmergencyStop : RunningTestScreenStateEvent
    data object NavigateToAddManualFaultDialog : RunningTestScreenStateEvent
}