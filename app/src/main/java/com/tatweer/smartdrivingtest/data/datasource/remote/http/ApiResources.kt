package com.tatweer.smartdrivingtest.data.datasource.remote.http

import io.ktor.resources.Resource
import kotlinx.serialization.SerialName

sealed interface ApiResources

@Resource("students")
class Students {

    @Resource("")
    class All(val parent: Students = Students()) : ApiResources

    @Resource("{id}")
    class Id(val parent: Students = Students(), val id: Int) : ApiResources

    @Resource("")
    class Search(val parent: Students = Students(), @SerialName("search") val query: String) :
        ApiResources
}