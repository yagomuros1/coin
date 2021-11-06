package com.yago.coin.data.utils

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.yago.coin.app.AppExecutors
import com.yago.coin.data.api.ApiRefreshTokenError
import com.yago.coin.data.api.ApiResponse
import com.yago.coin.data.api.ApiSuccessResponse
import com.yago.coin.data.api.response.UserTokenResponse

abstract class ApiNetwork(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<ApiResponse<UserTokenResponse>>()
    private var isTokenAlreadyRefreshed = false

    @MainThread
    private fun setValue(newValue: ApiResponse<UserTokenResponse>?) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    protected fun fetchRefreshToken(): MediatorLiveData<ApiResponse<UserTokenResponse>> {

        if (!isTokenAlreadyRefreshed) {

            isTokenAlreadyRefreshed = true

            val apiResponse = refreshTokenCall()
            result.addSource(apiResponse) { response ->
                when (response) {
                    is ApiSuccessResponse -> {
                        setValue(response)
                    }
                    else -> {
                        setValue(ApiRefreshTokenError(401, ""))
                    }
                }
            }
        }
        return result
    }

    @MainThread
    protected abstract fun refreshTokenCall(): LiveData<ApiResponse<UserTokenResponse>>

    @WorkerThread
    protected abstract fun processTryToRefreshResponse(userTokenResponse: ApiResponse<UserTokenResponse>?): Boolean

}
