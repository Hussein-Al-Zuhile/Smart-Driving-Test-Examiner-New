package com.tatweer.smartdrivingtest.domain.usecase.student

import com.tatweer.smartdrivingtest.data.repository.MainRepository
import com.tatweer.smartdrivingtest.domain.base.BaseUseCase
import com.tatweer.smartdrivingtest.domain.model.Student


class GetAllStudentsUseCase(private val repository: MainRepository) : BaseUseCase<List<Student>>() {
    operator fun invoke() = execute {
        repository.getStudents()
    }
}