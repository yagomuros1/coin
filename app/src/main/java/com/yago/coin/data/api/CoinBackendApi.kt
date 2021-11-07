package com.yago.coin.data.api

import androidx.lifecycle.LiveData
import com.yago.coin.data.api.response.RateResponse
import com.yago.coin.data.api.response.TransactionResponse
import retrofit2.http.GET

interface CoinBackendApi {

    @GET("rates.json")
    fun getRates(): LiveData<ApiResponse<List<RateResponse>>>

    @GET("transactions.json")
    fun getTrades(): LiveData<ApiResponse<List<TransactionResponse>>>

}