package com.tatweer.smartdrivingtest.di

import com.tatweer.smartdrivingtest.data.datasource.remote.http.InVehicleHttpClient
import com.tatweer.smartdrivingtest.data.base.TokenManager
import com.tatweer.smartdrivingtest.data.datasource.remote.http.MainService
import com.tatweer.smartdrivingtest.data.datasource.remote.mqtt.BackOfficeMQTTClient
import com.tatweer.smartdrivingtest.data.repository.MainRepository
import com.tatweer.smartdrivingtest.domain.usecase.inVehicle.PublishCarLocationUseCase
import com.tatweer.smartdrivingtest.domain.usecase.inVehicle.PublishCarOutOfRouteUseCase
import com.tatweer.smartdrivingtest.domain.usecase.inVehicle.PublishLanguageBasedOnNationalityUseCase
import com.tatweer.smartdrivingtest.domain.usecase.inVehicle.PublishLanguageUseCase
import com.tatweer.smartdrivingtest.domain.usecase.inVehicle.PublishReadingEIDUseCase
import com.tatweer.smartdrivingtest.domain.usecase.inVehicle.PublishVoiceCommandUseCase
import com.tatweer.smartdrivingtest.presentation.main.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val MainModule = module {
    single { InVehicleHttpClient }
    single { MainService(get()) }
    single { MainRepository(get(), get()) }
    single { BackOfficeMQTTClient() }


    //region UseCases
    factory { PublishLanguageUseCase(get()) }
    factory { PublishLanguageBasedOnNationalityUseCase(get()) }
    factory { PublishReadingEIDUseCase(get()) }
    factory { PublishCarLocationUseCase(get()) }
    factory { PublishVoiceCommandUseCase(get()) }
    factory { PublishCarOutOfRouteUseCase(get()) }
    //endregion

    single { TokenManager() }

    viewModelOf(::MainViewModel)
}