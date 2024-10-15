package com.tatweer.smartdrivingtest.presentation.driveTest.studentVerificationSkip

sealed interface StudentVerificationSkipScreenEvent {
    data object OnPhotoTaken : StudentVerificationSkipScreenEvent
    data object OnSkipScreenClicked : StudentVerificationSkipScreenEvent
    data object OnCancelClicked : StudentVerificationSkipScreenEvent
}