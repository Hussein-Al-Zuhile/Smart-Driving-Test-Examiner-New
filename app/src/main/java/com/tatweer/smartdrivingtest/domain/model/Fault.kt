package com.tatweer.smartdrivingtest.domain.model

data class Fault(
    val id: Int,
    val name: String,
    val isRejected: Boolean = false,
) {
    companion object {
        val ForPreview = Fault(
            1,
            "Starting The Engine",
            false
        )
    }
}
