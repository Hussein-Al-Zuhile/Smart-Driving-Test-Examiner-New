package com.tatweer.smartdrivingtest.di

import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.tatweer.smartdrivingtest.presentation.committee.CommitteeViewModel
import com.tatweer.smartdrivingtest.presentation.committee.studentList.StudentListViewModel
import com.tatweer.smartdrivingtest.presentation.committee.testRouteSelection.TestRouteSelectionViewModel
import com.tatweer.smartdrivingtest.presentation.driveTest.emergencyStop.EmergencyStopViewModel
import com.tatweer.smartdrivingtest.presentation.committee.vehicleInspectionForm.VehicleInspectionFormViewModel
import com.tatweer.smartdrivingtest.presentation.driveTest.studentDetails.StudentDetailsViewModel
import com.tatweer.smartdrivingtest.presentation.driveTest.studentVerification.StudentVerificationViewModel
import com.tatweer.smartdrivingtest.presentation.login.LoginViewModel
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

    //region ViewModels
    viewModelOf(::MainViewModel)

    //region Login ViewModels
    viewModelOf(::LoginViewModel)
    //endregion

    //region Committee ViewModels
    viewModelOf(::CommitteeViewModel)
    viewModelOf(::StudentListViewModel)
    viewModelOf(::VehicleInspectionFormViewModel)
    viewModelOf(::TestRouteSelectionViewModel)
    //endregion

    //region DriveTest ViewModels
    viewModelOf(::StudentDetailsViewModel)
    viewModelOf(::StudentVerificationViewModel)
    viewModelOf(::EmergencyStopViewModel)
    //endregion

    //endregion
}