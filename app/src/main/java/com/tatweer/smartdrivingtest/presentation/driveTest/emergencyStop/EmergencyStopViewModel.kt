package com.tatweer.smartdrivingtest.presentation.driveTest.emergencyStop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel

class EmergencyStopViewModel : BaseViewModel<EmergencyStopScreenStateEvent>() {

    var emergencyStopScreenState by mutableStateOf(EmergencyStopScreenState())
        private set

    fun onEvent(event: EmergencyStopScreenEvent) {
        when (event) {
            is EmergencyStopScreenEvent.OnEmergencyStopSelected -> {
                emergencyStopScreenState = emergencyStopScreenState.copy(selectedEmergencyStopType = event.emergencyStopType)
            }


            EmergencyStopScreenEvent.OnConfirmClicked -> {
                // Handle confirm click
            }

            EmergencyStopScreenEvent.OnCancelClicked -> {}
        }

    }
}