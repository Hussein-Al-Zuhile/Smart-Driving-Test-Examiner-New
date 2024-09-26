package com.tatweer.smartdrivingtest.domain.usecase.inVehicle

import com.tatweer.smartdrivingtest.data.repository.MainRepository
import com.tatweer.smartdrivingtest.domain.base.BaseUseCase
import com.tatweer.smartdrivingtest.domain.model.Location

class PublishLanguageUseCase(private val repository: MainRepository) : BaseUseCase<Unit>() {
    operator fun invoke(language: String) = execute {
        repository.publishLanguage(language)
    }
}