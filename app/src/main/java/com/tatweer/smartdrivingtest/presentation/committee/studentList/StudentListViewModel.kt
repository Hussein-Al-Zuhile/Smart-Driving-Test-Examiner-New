package com.tatweer.smartdrivingtest.presentation.committee.studentList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StudentListViewModel : BaseViewModel<StudentListStateEvent>() {

    var studentListScreenState by mutableStateOf(StudentListScreenState())
        private set

    fun onEventReceived(event: StudentListScreenEvent) {
        when (event) {
            StudentListScreenEvent.OnNextClicked -> {
                // Handled in the UI to navigate
            }

            is StudentListScreenEvent.OnStudentsFetched -> {
                studentListScreenState = studentListScreenState.copy(
                    students = event.students,
                    isLoading = false,
                    errorMessage = null
                )
            }

            is StudentListScreenEvent.OnStudentSelected -> {
                studentListScreenState =
                    studentListScreenState.copy(selectedStudent = event.student)
            }

            StudentListScreenEvent.OnStudentDeselected -> {
                studentListScreenState = studentListScreenState.copy(selectedStudent = null)
            }
        }
    }

    init {
        viewModelScope.launch {
            studentListScreenState = studentListScreenState.copy(isLoading = true)

            delay(1000)
            onEventReceived(
                StudentListScreenEvent.OnStudentsFetched(
                    listOf(
                        Student(1),
                        Student(2),
                        Student(3)
                    )
                )
            )
        }
    }
}