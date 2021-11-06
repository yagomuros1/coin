package com.yago.coin.domain.session

import com.yago.coin.data.db.CoinDb
import com.yago.coin.domain.customdata.UserTokenCustomData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val coinDb: CoinDb
) {

    fun login(userTokenDetails: UserTokenCustomData) {
        sharedPreferences.username = userTokenDetails.username
        updateTokenDetails(userTokenDetails.token, userTokenDetails.refreshToken)
    }

    fun updateTokenDetails(token: String, refreshToken: String) {
        sharedPreferences.token = token
        sharedPreferences.refreshToken = refreshToken
    }

}