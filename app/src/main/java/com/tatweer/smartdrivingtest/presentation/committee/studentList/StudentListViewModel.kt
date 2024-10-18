package com.tatweer.smartdrivingtest.presentation.committee.studentList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.domain.model.StudentStatus
import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StudentListViewModel : BaseViewModel<StudentListScreenStateEvent>() {

    var studentListScreenState by mutableStateOf(StudentListScreenState())
        private set

    fun onEvent(event: StudentListScreenEvent) {
        when (event) {
            StudentListScreenEvent.OnStartTestClicked -> {
                studentListScreenState = studentListScreenState.copy(isTestStarted = true)
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

            is StudentListScreenEvent.OnStudentAbsentClicked -> {
                studentListScreenState = studentListScreenState.copy(selectedStudent = null)
            }

            is StudentListScreenEvent.OnStudentAttendOnBusClicked -> {
                studentListScreenState = studentListScreenState.copy(selectedStudent = null)
            }

            is StudentListScreenEvent.OnStudentTestStarted -> {
                studentListScreenState = studentListScreenState.copy(selectedStudent = null)

                _singleStateEventChannel.sendInViewModelScope(
                    StudentListScreenStateEvent.OnStudentTestStarted(event.student)
                )
            }
        }
    }

    init {
        viewModelScope.launch {
            studentListScreenState = studentListScreenState.copy(isLoading = true)

            delay(1000)
            onEvent(
                StudentListScreenEvent.OnStudentsFetched(
                    listOf(
                        Student(
                            id = 3880,
                            name = "Cedric Finch",
                            emiratesId = "veniam",
                            status = StudentStatus.NotStarted
                        ),
                        Student(
                            id = 6314,
                            name = "Colleen Sanchez",
                            emiratesId = "eius",
                            status = StudentStatus.NotStarted
                        ),
                        Student(
                            id = 8114,
                            name = "Thad George",
                            emiratesId = "persecuti",
                            status = StudentStatus.NotStarted
                        )
                    )
                )
            )
        }
    }
}