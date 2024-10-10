package com.tatweer.smartdrivingtest.presentation.driveTest.emergencyStop

interface EmergencyStopScreenEvent {
    data class OnEmergencyStopSelected(val emergencyStopType: EmergencyStopType) : EmergencyStopScreenEvent
    object OnConfirmClicked : EmergencyStopScreenEvent
    object OnCancelClicked : EmergencyStopScreenEvent
}