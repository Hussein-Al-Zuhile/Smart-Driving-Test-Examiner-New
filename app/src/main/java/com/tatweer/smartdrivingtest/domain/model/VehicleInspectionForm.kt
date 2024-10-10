package com.tatweer.smartdrivingtest.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class VehicleInspectionForm(
    val commission: Int = 2131231,
    val license: Int = 21312312,
    val carMechanicalError: Boolean = false,
    val unclean: Boolean = false,
    val vehicleNotes: String = "",
    val systemNotes: String = "",
    val systemNotesDescription: String = "",
)
