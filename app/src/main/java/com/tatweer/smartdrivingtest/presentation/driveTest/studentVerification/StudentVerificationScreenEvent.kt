package com.tatweer.smartdrivingtest.presentation.driveTest.studentVerification

sealed interface StudentVerificationScreenEvent {
    data object OnSkipClicked : StudentVerificationScreenEvent
    data object OnCancelClicked : StudentVerificationScreenEvent
    data class OnNoteChanged(val note: String) : StudentVerificationScreenEvent
    data object OnTakePhotoClicked : StudentVerificationScreenEvent
}