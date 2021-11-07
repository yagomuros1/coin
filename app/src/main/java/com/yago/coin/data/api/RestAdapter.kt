package com.yago.coin.data.api

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.yago.coin.domain.livedata.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RestAdapter @Inject constructor(private val app: Application, private val requestInterceptor: RequestInterceptor) {
    fun getCoinBackendApi(): CoinBackendApi {
        return Retrofit.Builder()
            .baseUrl("http://quiet-stone-2094.herokuapp.com/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
                )
            )
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(OkHttpClient.Builder().addInterceptor(requestInterceptor).build())
            .build()
            .create(CoinBackendApi::class.java)
    }

}