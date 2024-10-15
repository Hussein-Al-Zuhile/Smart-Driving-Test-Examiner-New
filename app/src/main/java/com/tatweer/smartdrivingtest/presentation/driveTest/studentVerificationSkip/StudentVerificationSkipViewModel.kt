package com.tatweer.smartdrivingtest.presentation.driveTest.studentVerificationSkip

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel

class StudentVerificationSkipViewModel : BaseViewModel<StudentVerificationSkipScreenStateEvent>() {

    var state by mutableStateOf(StudentVerificationSkipScreenState())
        private set

    fun onEvent(event: StudentVerificationSkipScreenEvent) {
        when (event) {
            StudentVerificationSkipScreenEvent.OnSkipScreenClicked -> _singleStateEventChannel.sendInViewModelScope(
                StudentVerificationSkipScreenStateEvent.NavigateToRunningTest
            )

            StudentVerificationSkipScreenEvent.OnCancelClicked -> _singleStateEventChannel.sendInViewModelScope(
                StudentVerificationSkipScreenStateEvent.NavigateUp
            )

            StudentVerificationSkipScreenEvent.OnPhotoTaken -> state =
                state.copy(isPhotoTaken = true)
        }
    }
}