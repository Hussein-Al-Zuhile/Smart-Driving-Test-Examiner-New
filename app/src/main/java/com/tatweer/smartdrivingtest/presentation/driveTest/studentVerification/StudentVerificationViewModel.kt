package com.tatweer.smartdrivingtest.presentation.driveTest.studentVerification

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel

class StudentVerificationViewModel : BaseViewModel<StudentVerificationScreenStateEvent>() {

    var studentVerificationScreenState by mutableStateOf(StudentVerificationScreenState.Initial)
        private set

    fun onEvent(event: StudentVerificationScreenEvent) {
        when (event) {
            StudentVerificationScreenEvent.OnCancelClicked -> TODO()
            is StudentVerificationScreenEvent.OnNoteChanged -> TODO()
            StudentVerificationScreenEvent.OnSkipClicked -> TODO()
            StudentVerificationScreenEvent.OnTakePhotoClicked -> TODO()
        }

    }

}