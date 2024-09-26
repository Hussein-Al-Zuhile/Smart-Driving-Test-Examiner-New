package com.tatweer.smartdrivingtest.domain.base

sealed class Result<T>() {

    abstract val message: String?

    companion object {
        fun <T> initial(message: String? = null) = Initial<T>(message)

        fun <T> loading(message: String? = null) = Loading.Generic<T>(message)

        fun <T> success(data: T? = null, message: String? = null): Success<T> = if (data != null) {
            Success.Data(data, message)
        } else {
            Success.Generic<T>(message)
        }

        fun <T> successWithData(data: T, message: String? = null) = Success.Data(data, message)

        fun <T> failure(message: String? = null) = Failure.Generic<T>(message)
    }

    data class Initial<T>(override val message: String? = null) : Result<T>()

    sealed class Loading<T> : Result<T>() {
        data class Generic<T>(override val message: String? = null) : Loading<T>()

        data class Refreshing<T>(override val message: String? = null) : Loading<T>()
    }

    sealed class Success<T> : Result<T>() {
        data class Generic<T>(override val message: String? = null) : Success<T>()

        data class Data<T>(
            val data: T,
            override val message: String? = null,
        ) : Success<T>()
    }

    sealed class Failure<T> : Result<T>() {
        data class Generic<T>(override val message: String? = null) : Failure<T>()

        data class NoInternetConnection<T>(override val message: String? = null) : Failure<T>()

        data class ServerErrorException<T>(override val message: String? = null) : Failure<T>()

        data class BadRequestException<T>(override val message: String? = null) : Failure<T>()

        data class Unauthorized<T>(override val message: String? = null) : Failure<T>()

        data class QueryIsEmpty<T>(override val message: String? = null) : Failure<T>()

        sealed class MQTT<T> : Failure<T>() {
            data class SubscriptionFailed<T>(override val message: String? = null) : MQTT<T>()
        }
    }

    val isLoading: Boolean
        get() = this is Loading

    val isSuccess: Boolean
        get() = this is Success

    val isSuccessWithData: Boolean
        get() = this is Success.Data

    val isFailure: Boolean
        get() = this is Failure
}