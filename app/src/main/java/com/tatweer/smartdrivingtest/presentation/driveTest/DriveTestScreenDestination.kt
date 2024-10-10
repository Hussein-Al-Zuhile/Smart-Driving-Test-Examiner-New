package com.tatweer.smartdrivingtest.presentation.driveTest

import kotlinx.serialization.Serializable

sealed interface DriveTestScreenDestination {
    @Serializable
    data object UserDetails : DriveTestScreenDestination

    @Serializable
    data object UserVerification : DriveTestScreenDestination

    @Serializable
    data object StudentVerificationSkip : DriveTestScreenDestination

    @Serializable
    data object EmergencyStop : DriveTestScreenDestination
}