package com.tatweer.smartdrivingtest.domain.model

data class Location(
    val longitude: Double,
    val latitude: Double,
    val altitude: Double,
    val speed: Double,
    val course: Double,
    val horizontalAccuracy: Double,
    val verticalAccuracy: Double
)