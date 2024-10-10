package com.tatweer.smartdrivingtest.presentation.driveTest.emergencyStop

import com.tatweer.smartdrivingtest.presentation.base.BaseUIState

data class EmergencyStopScreenState(
    val selectedEmergencyStopType: EmergencyStopType? = null,
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
) : BaseUIState()
