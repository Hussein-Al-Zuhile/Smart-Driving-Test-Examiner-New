package com.tatweer.smartdrivingtest.domain.usecase.inVehicle

import com.tatweer.smartdrivingtest.data.repository.MainRepository
import com.tatweer.smartdrivingtest.domain.base.BaseUseCase

class PublishLanguageBasedOnNationalityUseCase(private val repository: MainRepository) : BaseUseCase<Unit>() {
    operator fun invoke(language: String) = execute {
        repository.publishLanguageBasedOnNationality(language)
    }
}
