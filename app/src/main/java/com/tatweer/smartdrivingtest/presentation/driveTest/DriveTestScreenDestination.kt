package com.tatweer.smartdrivingtest.presentation.driveTest

import kotlinx.serialization.Serializable

sealed interface DriveTestScreenDestination {
    @Serializable
    data object UserDetails : DriveTestScreenDestination

    @Serializable
    data object StudentVerification : DriveTestScreenDestination

    @Serializable
    data object StudentVerificationSkip : DriveTestScreenDestination

    @Serializable
    data object RunningTest : DriveTestScreenDestination

    @Serializable
    data object AddManualFault : DriveTestScreenDestination

    @Serializable
    data object EmergencyStop : DriveTestScreenDestination
}