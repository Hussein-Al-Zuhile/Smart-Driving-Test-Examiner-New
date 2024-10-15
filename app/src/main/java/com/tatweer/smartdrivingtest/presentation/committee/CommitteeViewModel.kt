package com.tatweer.smartdrivingtest.presentation.committee

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel

class CommitteeViewModel : BaseViewModel<CommitteeScreenStateEvent>() {

    var committeeScreenState by mutableStateOf(CommitteeScreenState())
        private set

    fun onEvent(event: CommitteeScreenEvent) {
        when (event) {
            is CommitteeScreenEvent.OnStudentTestStarted -> {
                committeeScreenState = committeeScreenState.copy(startedStudent = event.student)
            }

            CommitteeScreenEvent.OnStudentTestFinished -> {
                committeeScreenState = committeeScreenState.copy(startedStudent = null)
            }
        }
    }
}
