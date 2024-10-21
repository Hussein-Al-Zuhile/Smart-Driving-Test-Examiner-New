package com.tatweer.smartdrivingtest.domain.model

data class Fault(
    val id: Int,
    val name: String,
    val isRejected: Boolean = false,
    val availablePlaces: Set<FaultPlaceType>,
    val addedInPlaces: Set<FaultPlaceType>
) {
    companion object {
        val ForPreview = Fault(
            1,
            "Starting The Engine",
            false,
            availablePlaces = setOf(FaultPlaceType.StartUp, FaultPlaceType.MovementAndParking),
            addedInPlaces = setOf()
        )
    }

    fun toggle(place: FaultPlaceType): Fault {
        return if (addedInPlaces.contains(place)) {
            copy(addedInPlaces = addedInPlaces - place)
        } else {
            copy(addedInPlaces = addedInPlaces + place)
        }
    }
}
