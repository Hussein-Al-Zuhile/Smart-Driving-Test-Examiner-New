package com.tatweer.smartdrivingtest.presentation.committee.vehicleInspectionForm

import com.tatweer.smartdrivingtest.domain.model.Student

sealed interface VehicleInspectionFormScreenEvent {

    // TODO: Remove this event if not necessary
    data class InitialEvent(val students: List<Student>) : VehicleInspectionFormScreenEvent

    data object OnCancelClicked : VehicleInspectionFormScreenEvent
    data object OnSendClicked : VehicleInspectionFormScreenEvent

    data class OnUncleanChanged(val unclean: Boolean) : VehicleInspectionFormScreenEvent
    data class OnCarMechanicalErrorChanged(val carMechanicalError: Boolean) :
        VehicleInspectionFormScreenEvent

    data class OnVehicleNotesChanged(val vehicleNotes: String) : VehicleInspectionFormScreenEvent
    data class OnSystemNotesChanged(val systemNotes: String) : VehicleInspectionFormScreenEvent
    data class OnSystemNotesDescriptionChanged(val systemNotesDescription: String) :
        VehicleInspectionFormScreenEvent
}