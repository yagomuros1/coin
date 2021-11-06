package com.yago.coin.ui.views.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.yago.coin.R
import com.yago.coin.ui.views.main.MainActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        const val SPLASH_DELAY = 1000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            MainActivity().start(this)
        }, SPLASH_DELAY)

    }
}