package com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault

import com.tatweer.smartdrivingtest.domain.model.Fault
import com.tatweer.smartdrivingtest.domain.model.FaultPlaceType

sealed interface AddManualFaultDialogEvent {
    data class OnToggleAddFaultToPlace(val fault: Fault, val placeType: FaultPlaceType) :
        AddManualFaultDialogEvent

    data object OnConfirmAddedFaults : AddManualFaultDialogEvent

    data object OnClickOutsideDialog : AddManualFaultDialogEvent
}