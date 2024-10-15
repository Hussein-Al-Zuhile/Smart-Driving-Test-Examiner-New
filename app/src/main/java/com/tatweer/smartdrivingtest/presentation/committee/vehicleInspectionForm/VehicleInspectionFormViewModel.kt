package com.tatweer.smartdrivingtest.presentation.committee.vehicleInspectionForm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel

class VehicleInspectionFormViewModel : BaseViewModel<VehicleInspectionFormScreenStateEvent>() {

    var vehicleInspectionFormScreenState by mutableStateOf(VehicleInspectionFormScreenState())
        private set

    fun onEvent(event: VehicleInspectionFormScreenEvent) {
        when (event) {
            is VehicleInspectionFormScreenEvent.InitialEvent -> {
                vehicleInspectionFormScreenState = vehicleInspectionFormScreenState.copy(
                    students = event.students
                )
            }

            is VehicleInspectionFormScreenEvent.OnCarMechanicalErrorChanged -> {
                vehicleInspectionFormScreenState = vehicleInspectionFormScreenState.copy(
                    vehicleInspectionForm = vehicleInspectionFormScreenState.vehicleInspectionForm.copy(
                        carMechanicalError = event.carMechanicalError
                    )
                )
            }

            is VehicleInspectionFormScreenEvent.OnUncleanChanged -> {
                vehicleInspectionFormScreenState = vehicleInspectionFormScreenState.copy(
                    vehicleInspectionForm = vehicleInspectionFormScreenState.vehicleInspectionForm.copy(
                        unclean = event.unclean
                    )
                )
            }

            is VehicleInspectionFormScreenEvent.OnVehicleNotesChanged -> {
                vehicleInspectionFormScreenState = vehicleInspectionFormScreenState.copy(
                    vehicleInspectionForm = vehicleInspectionFormScreenState.vehicleInspectionForm.copy(
                        vehicleNotes = event.vehicleNotes
                    )
                )
            }

            is VehicleInspectionFormScreenEvent.OnSystemNotesChanged -> {
                vehicleInspectionFormScreenState = vehicleInspectionFormScreenState.copy(
                    vehicleInspectionForm = vehicleInspectionFormScreenState.vehicleInspectionForm.copy(
                        systemNotes = event.systemNotes
                    )
                )
            }

            is VehicleInspectionFormScreenEvent.OnSystemNotesDescriptionChanged -> {
                vehicleInspectionFormScreenState = vehicleInspectionFormScreenState.copy(
                    vehicleInspectionForm = vehicleInspectionFormScreenState.vehicleInspectionForm.copy(
                        systemNotesDescription = event.systemNotesDescription
                    )
                )
            }

            // Handled in UI
            VehicleInspectionFormScreenEvent.OnCancelClicked -> {}
            VehicleInspectionFormScreenEvent.OnSendClicked -> {}
        }
    }
}