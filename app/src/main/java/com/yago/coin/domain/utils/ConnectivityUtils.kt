package com.yago.coin.domain.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object ConnectivityUtils {

    private fun checkNetworkConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

    fun checkNetWorkAndExecuteCallback(context: Activity, success: (() -> Unit)? = null, error: (() -> Unit)? = null) {
        if (checkNetworkConnection(context)) {
            success?.let {
                it()
            }
        } else {
            error?.let {
                it()
            }
        }
    }

}