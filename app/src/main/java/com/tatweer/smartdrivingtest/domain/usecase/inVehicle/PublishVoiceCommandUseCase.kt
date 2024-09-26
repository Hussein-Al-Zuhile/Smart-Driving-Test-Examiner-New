package com.tatweer.smartdrivingtest.domain.usecase.inVehicle

import com.tatweer.smartdrivingtest.data.repository.MainRepository
import com.tatweer.smartdrivingtest.domain.base.BaseUseCase

class PublishVoiceCommandUseCase(private val repository: MainRepository) : BaseUseCase<Unit>() {
    operator fun invoke(command: String) = execute {
        repository.publishVoiceCommand(command)
    }
}
