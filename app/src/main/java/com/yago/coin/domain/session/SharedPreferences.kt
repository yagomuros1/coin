package com.yago.coin.domain.session

import android.app.Application
import android.content.Context
import com.yago.coin.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferences @Inject constructor(app: Application) {

    private companion object {
        private const val KEY_USERNAME = "_username"
        private const val KEY_TOKEN = "_token"
        private const val KEY_REFRESH_TOKEN = "_refreshToken"
        private const val KEY_FIRST_LAUNCH = "_firstLaunch"
    }

    private val prefs: android.content.SharedPreferences = app.getSharedPreferences(
        app.getString(R.string.shared_preferences_name),
        Context.MODE_PRIVATE
    )

    var username: String?
        get() = prefs.getString(KEY_USERNAME, null)
        set(value) {
            prefs.edit().putString(KEY_USERNAME, value).apply()
        }

    var token: String?
        get() = prefs.getString(KEY_TOKEN, null)
        set(value) {
            prefs.edit().putString(KEY_TOKEN, value).apply()
        }

    var refreshToken: String?
        get() = prefs.getString(KEY_REFRESH_TOKEN, null)
        set(value) {
            prefs.edit().putString(KEY_REFRESH_TOKEN, value).apply()
        }

    var isFirstLaunch: Boolean
        get() = prefs.getBoolean(KEY_FIRST_LAUNCH, true)
        set(value) {
            prefs.edit().putBoolean(KEY_FIRST_LAUNCH, value).apply()
        }

    fun logout() {
        username = null
        token = null
        refreshToken = null
    }

    fun clear() {
        prefs.edit().clear().apply()
    }

}