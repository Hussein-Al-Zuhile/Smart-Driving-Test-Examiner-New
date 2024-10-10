package com.tatweer.smartdrivingtest.presentation.committee.vehicleInspectionForm

import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.domain.model.VehicleInspectionForm
import com.tatweer.smartdrivingtest.presentation.base.BaseUIState

data class VehicleInspectionFormScreenState(
    val students: List<Student> = emptyList(),
    val vehicleInspectionForm: VehicleInspectionForm = VehicleInspectionForm(),
) : BaseUIState()
