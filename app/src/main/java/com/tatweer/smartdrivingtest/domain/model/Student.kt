package com.tatweer.smartdrivingtest.domain.model

import androidx.annotation.StringRes
import com.tatweer.smartdrivingtest.R
import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: Int,
    val name: String,
    val emiratesId: String,
    val status: StudentStatus
) {
    companion object {
        val ForPreview = Student(
            id = 9623,
            name = "Joesph Maldonado",
            emiratesId = "213123123",
            status = StudentStatus.NotStarted
        )
    }
}


@Serializable
enum class StudentStatus(@StringRes val titleStringRes: Int) {
    NotStarted(R.string.label_not_started),
    Absent(R.string.label_absent),
    Completed(R.string.label_completed),
    Uncompleted(R.string.label_uncompleted)
}
