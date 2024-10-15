package com.tatweer.smartdrivingtest.presentation.driveTest.runningTest

import com.tatweer.smartdrivingtest.presentation.base.StateEvent

interface RunningTestScreenStateEvent : StateEvent {
    data object NavigateToEmergencyStop : RunningTestScreenStateEvent
    data object NavigateToAddManualFaultDialog : RunningTestScreenStateEvent
}