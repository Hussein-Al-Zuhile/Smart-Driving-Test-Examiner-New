package com.tatweer.smartdrivingtest.presentation.committee.studentList

import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.presentation.base.BaseUIState

data class StudentListScreenState(
    val students: List<Student> = emptyList(),
    val selectedStudent: Student? = null,
    val isTestStarted: Boolean = false,
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
) : BaseUIState()