package com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel

class AddManualFaultViewModel : BaseViewModel<AddManualFaultDialogStateEvent>() {

    var state by mutableStateOf(AddManualFaultDialogState())
        private set

    fun onEvent(event: AddManualFaultDialogEvent) {
        when (event) {
            is AddManualFaultDialogEvent.OnToggleAddFaultToPlace -> {
                state = state.copy(
                    faultGroups = state.faultGroups.map { faultGroup ->
                        val updatedFaults = faultGroup.faults.map { fault ->
                            if (fault == event.fault) {
                                val toggledFault = fault.toggle(event.placeType)
                                toggledFault
                            } else
                                fault
                        }
                        faultGroup.copy(faults = updatedFaults)
                    }
                )
            }

            is AddManualFaultDialogEvent.OnConfirmAddedFaults -> {
                _singleStateEventChannel.sendInViewModelScope(
                    AddManualFaultDialogStateEvent.ConfirmEditingFaults(
                        state.faultGroups
                    )
                )
            }

            AddManualFaultDialogEvent.OnClickOutsideDialog -> {
                _singleStateEventChannel.sendInViewModelScope(AddManualFaultDialogStateEvent.NavigateUp)
            }
        }
    }
}