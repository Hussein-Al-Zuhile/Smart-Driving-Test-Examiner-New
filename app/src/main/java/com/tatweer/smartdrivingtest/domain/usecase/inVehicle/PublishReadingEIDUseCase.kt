package com.tatweer.smartdrivingtest.domain.usecase.inVehicle

import com.tatweer.smartdrivingtest.data.repository.MainRepository
import com.tatweer.smartdrivingtest.domain.base.BaseUseCase

class PublishReadingEIDUseCase(private val repository: MainRepository) : BaseUseCase<Unit>() {
    operator fun invoke() = execute {
        repository.publishReadingEID()
    }
}