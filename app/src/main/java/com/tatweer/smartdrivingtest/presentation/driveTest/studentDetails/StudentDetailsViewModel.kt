package com.tatweer.smartdrivingtest.presentation.driveTest.studentDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel

class StudentDetailsViewModel : BaseViewModel<StudentDetailsScreenStateEvent>() {

    var studentDetailsScreenState by mutableStateOf(StudentDetailsScreenState.Initial)
        private set

    fun onEvent(event: StudentDetailsScreenEvent) {
        when (event) {
            StudentDetailsScreenEvent.OnAbsentClicked -> TODO()
            StudentDetailsScreenEvent.OnChangeRouteClicked -> TODO()
            is StudentDetailsScreenEvent.OnRouteSelected -> TODO()
            StudentDetailsScreenEvent.OnVerifyStudentClicked -> {
                // Handled in UI
            }
        }
    }
}