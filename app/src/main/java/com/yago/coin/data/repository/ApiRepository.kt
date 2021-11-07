package com.yago.coin.data.repository

import androidx.lifecycle.LiveData
import com.yago.coin.data.api.ApiResponse
import com.yago.coin.data.api.response.UserTokenResponse
import com.yago.coin.domain.livedata.AbsentLiveData

abstract class ApiRepository {

    protected fun tryToRefreshToken(): LiveData<ApiResponse<UserTokenResponse>> {
        //TO-COMPLETE
        return AbsentLiveData.create()
    }

    protected fun processRefreshToken(): Boolean {
        //TO-COMPLETE
        return true
    }

}