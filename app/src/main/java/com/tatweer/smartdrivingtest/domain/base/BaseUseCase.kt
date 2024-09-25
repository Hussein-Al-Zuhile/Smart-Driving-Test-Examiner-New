package com.tatweer.smartdrivingtest.domain.base

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<T> {

    inline fun execute(crossinline request: suspend () -> Result<T>) = flow<Result<T>> {
        emit(Result.loading())

        emit(request())
    }.catch {
        emit(Result.failure(it.message))
    }
}