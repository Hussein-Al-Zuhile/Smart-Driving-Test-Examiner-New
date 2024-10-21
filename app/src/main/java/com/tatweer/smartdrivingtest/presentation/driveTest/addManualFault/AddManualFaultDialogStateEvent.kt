package com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault

import com.tatweer.smartdrivingtest.domain.model.Fault
import com.tatweer.smartdrivingtest.domain.model.FaultGroup
import com.tatweer.smartdrivingtest.domain.model.FaultPlaceType
import com.tatweer.smartdrivingtest.presentation.base.StateEvent

sealed interface AddManualFaultDialogStateEvent : StateEvent {
    data class ConfirmEditingFaults(val faultGroups: List<FaultGroup>) :
        AddManualFaultDialogStateEvent

    data object NavigateUp : AddManualFaultDialogStateEvent
}