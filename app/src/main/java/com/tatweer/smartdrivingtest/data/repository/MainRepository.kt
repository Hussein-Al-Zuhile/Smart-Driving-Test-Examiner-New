package com.tatweer.smartdrivingtest.data.repository

import com.tatweer.smartdrivingtest.data.base.BaseRepository
import com.tatweer.smartdrivingtest.data.datasource.remote.http.MainService
import com.tatweer.smartdrivingtest.domain.model.Student


class MainRepository(private val mainService: MainService) : BaseRepository() {

    suspend fun getStudents() = sendRemoteRequest<List<Student>> { mainService.getAllStudents() }

    suspend fun getStudent(id: Int) = sendRemoteRequest<Student> { mainService.getStudent(id) }

    suspend fun searchStudents(query: String) = sendRemoteRequest<List<Student>> { mainService.searchStudents(query) }

}