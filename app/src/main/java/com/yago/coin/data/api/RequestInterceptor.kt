package com.yago.coin.data.api

import android.app.Application
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject

class RequestInterceptor @Inject constructor(private val app: Application) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            getHeaders().forEach { addHeader(it.key, it.value) }
        }.build()
        return chain.proceed(request)
    }

    private fun getHeaders(): Map<String, String> =
        HashMap<String, String>().apply {
            put("Accept", "application/json")
        }

}