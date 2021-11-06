package com.yago.coin.data.api

import androidx.lifecycle.LiveData
import com.yago.coin.data.api.response.DataResponse
import retrofit2.http.GET

interface CoinBackendApi {

    @GET("rates.json")
    fun getData(): LiveData<ApiResponse<DataResponse>>

}