package com.tatweer.smartdrivingtest.presentation.driveTest

import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel

class DriveTestViewModel : BaseViewModel<DriveTestScreenStateEvent>() {

    fun onEvent(event: DriveTestScreenStateEvent) {
        when (event) {
            is DriveTestScreenStateEvent.OnConfirmAddedFaults -> {

            }
        }
    }
}