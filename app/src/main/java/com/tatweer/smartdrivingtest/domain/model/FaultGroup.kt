package com.tatweer.smartdrivingtest.domain.model

data class FaultGroup(
    val id: Int,
    val name: String,
    val iconUrl: String,
    val faults: List<Fault>,
) {
    companion object {
        val ForPreview = FaultGroup(
            1,
            "Starting The Engine",
            "",
            listOf(Fault.ForPreview, Fault.ForPreview, Fault.ForPreview)
        )
    }
}
