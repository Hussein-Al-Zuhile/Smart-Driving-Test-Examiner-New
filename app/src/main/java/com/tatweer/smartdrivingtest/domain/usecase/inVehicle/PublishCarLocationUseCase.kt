package com.tatweer.smartdrivingtest.domain.usecase.inVehicle

import com.tatweer.smartdrivingtest.data.repository.MainRepository
import com.tatweer.smartdrivingtest.domain.base.BaseUseCase
import com.tatweer.smartdrivingtest.domain.model.Location

class PublishCarLocationUseCase(private val repository: MainRepository) : BaseUseCase<Unit>() {
    operator fun invoke(location: Location) = execute {
        repository.publishCarLocation(location)
    }
}
