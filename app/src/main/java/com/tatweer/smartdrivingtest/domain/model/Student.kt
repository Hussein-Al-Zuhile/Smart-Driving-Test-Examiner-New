package com.tatweer.smartdrivingtest.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: Int,
    val name: String,
)
