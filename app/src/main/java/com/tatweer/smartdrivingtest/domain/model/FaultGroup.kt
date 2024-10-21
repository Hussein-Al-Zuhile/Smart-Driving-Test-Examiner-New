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
            listOf(
                Fault(
                    id = 6988,
                    name = "Milagros Bolton",
                    isRejected = false,
                    availablePlaces = setOf(FaultPlaceType.StartUp),
                    addedInPlaces = setOf()
                ), Fault(
                    id = 7361,
                    name = "Darcy Miller",
                    isRejected = false,
                    availablePlaces = setOf(
                        FaultPlaceType.MovementAndParking,
                        FaultPlaceType.RoundAboutAndIntersection
                    ),
                    addedInPlaces = setOf()
                ), Fault(
                    id = 5238,
                    name = "Dawn Nielsen",
                    isRejected = false,
                    availablePlaces = setOf(
                        FaultPlaceType.StartUp,
                        FaultPlaceType.RoundAboutAndIntersection
                    ),
                    addedInPlaces = setOf()
                )
            )
        )
    }
}
