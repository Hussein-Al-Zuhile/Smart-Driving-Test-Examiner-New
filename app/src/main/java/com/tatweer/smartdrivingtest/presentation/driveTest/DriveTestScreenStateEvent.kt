package com.tatweer.smartdrivingtest.presentation.driveTest

import com.tatweer.smartdrivingtest.domain.model.FaultGroup
import com.tatweer.smartdrivingtest.presentation.base.StateEvent
import com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault.AddManualFaultDialogEvent

sealed interface DriveTestScreenStateEvent : StateEvent {
    data class OnConfirmAddedFaults(val faultGroups: List<FaultGroup>) : DriveTestScreenStateEvent
}