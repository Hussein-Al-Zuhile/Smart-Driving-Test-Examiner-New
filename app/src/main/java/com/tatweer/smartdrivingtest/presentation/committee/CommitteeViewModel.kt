package com.tatweer.smartdrivingtest.presentation.committee

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel

class CommitteeViewModel : BaseViewModel<CommitteeScreenStateEvent>() {

    var committeeScreenState by mutableStateOf(CommitteeScreenState2())
        private set

    fun onEventReceived(event: CommitteeScreenEvent) {
        when (event) {
            is CommitteeScreenEvent.OnStudentsFetched -> {
                committeeScreenState = committeeScreenState.copy(students = event.students)
            }

            else -> {}
        }
    }
}
