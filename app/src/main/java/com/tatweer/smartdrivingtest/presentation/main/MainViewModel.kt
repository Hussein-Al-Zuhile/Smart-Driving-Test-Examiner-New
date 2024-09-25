package com.tatweer.smartdrivingtest.presentation.main

import com.tatweer.smartdrivingtest.domain.usecase.student.GetAllStudentsUseCase
import com.tatweer.smartdrivingtest.domain.usecase.student.GetStudentUseCase
import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel

class MainViewModel(
    private val getAllStudentsUseCase: GetAllStudentsUseCase,
    private val getStudentUseCase: GetStudentUseCase,
) : BaseViewModel() {


    fun getAllStudents() = getAllStudentsUseCase()
}