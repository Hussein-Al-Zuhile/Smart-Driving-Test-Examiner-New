package com.tatweer.smartdrivingtest.domain.usecase.student

import com.tatweer.smartdrivingtest.data.repository.MainRepository
import com.tatweer.smartdrivingtest.domain.base.BaseUseCase
import com.tatweer.smartdrivingtest.domain.base.Result
import com.tatweer.smartdrivingtest.domain.model.Student


class SearchStudentsUseCase(private val repository: MainRepository) : BaseUseCase<List<Student>>() {
    operator fun invoke(query:String) = execute {
        if (query.isBlank()) return@execute Result.Failure.QueryIsEmpty()
        repository.searchStudents(query)
    }
}