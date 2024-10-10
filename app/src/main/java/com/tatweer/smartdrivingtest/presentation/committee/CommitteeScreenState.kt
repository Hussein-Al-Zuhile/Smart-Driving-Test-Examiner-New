package com.tatweer.smartdrivingtest.presentation.committee

import com.tatweer.smartdrivingtest.domain.model.Student
import kotlinx.serialization.Serializable

// TODO: Check if we want them latter on, if not delete them
sealed class CommitteeScreenState {
    @Serializable
    data object Initial : CommitteeScreenState()

    @Serializable
    data object Loading : CommitteeScreenState()
    sealed class Success {

        @Serializable
        data class StudentsFetched(val students: List<Student>) : CommitteeScreenState()

        @Serializable
        data class VehicleInspectionForm(
            val students: List<Student>,
            val commission: Int,
            val license: Int,
            val carMechanicalError: Boolean = false,
            val unclean: Boolean = false,
            val vehicleNotes: String = "",
            val systemNotes: String = "",
            val systemNotesDescription: String = "",
        ) :
            CommitteeScreenState()

        @Serializable
        data class RouteSelection(
            val students: List<Student>,
            val commission: Int,
            val license: Int,
            val carMechanicalError: Boolean,
            val unclean: Boolean,
            val vehicleNotes: String,
            val systemNotes: String,
            val routeId: Int? = null,
        ) : CommitteeScreenState()
    }

    @Serializable
    data class Error(val message: String) : CommitteeScreenState()
}

data class CommitteeScreenState2(
    val students: List<Student> = emptyList(),
    val commission: Int? = null,
    val license: Int? = null,
    val carMechanicalError: Boolean = false,
    val unclean: Boolean = false,
    val vehicleNotes: String = "",
    val systemNotes: String = "",
    val systemNotesDescription: String = "",
    val routeId: Int? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)