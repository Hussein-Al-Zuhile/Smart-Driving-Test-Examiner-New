package com.tatweer.smartdrivingtest.presentation.driveTest.emergencyStop

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarCrash
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.ui.graphics.vector.ImageVector
import com.tatweer.smartdrivingtest.R

enum class EmergencyStopType(@StringRes val nameRes: Int, val icon: ImageVector) {
    Medical(R.string.text_medical, Icons.Default.MedicalServices),
    Accident(R.string.text_accident, Icons.Default.CarCrash),
    Mechanical(R.string.text_mechanical, Icons.Default.Construction),
}
